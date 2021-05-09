package com.gars.percents.details.mvi

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.gars.percents.details.data.AccrualItem
import com.gars.percents.details.domain.CalculatorUseCase
import com.gars.percents.details.mvi.DetailsFeature.Effect
import com.gars.percents.details.mvi.DetailsFeature.Effect.ResultError
import com.gars.percents.details.mvi.DetailsFeature.Effect.ResultSuccess
import com.gars.percents.details.mvi.DetailsFeature.Effect.StartedLoading
import com.gars.percents.details.mvi.DetailsFeature.State
import com.gars.percents.details.mvi.DetailsFeature.Wish
import com.gars.percents.home.data.Percent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DetailsFeature(
    percent: Percent,
    calculatorUseCase: CalculatorUseCase
) : ActorReducerFeature<Wish, Effect, State, Nothing>(
    initialState = State(status = State.Status.IDLE),
    actor = ActorImpl(calculatorUseCase, percent),
    reducer = ReducerImpl()
) {

    data class State(
        val status: Status,
        val month: String = "",
        val accrual: List<AccrualItem> = emptyList(),

        ) {
        enum class Status {
            IDLE,
            LOADING,
            DATA,
            ERROR
        }
    }

    sealed class Wish {
        object Load : Wish()
    }

    sealed class Effect {
        object StartedLoading : Effect()
        data class ResultSuccess(val actuals: List<AccrualItem>) : Effect()
        data class ResultError(val error: Throwable) : Effect()
    }

    class ActorImpl(
        private val calculatorUseCase: CalculatorUseCase,
        private val percent: Percent,
    ) : Actor<State, Wish, Effect> {

        override fun invoke(state: State, action: Wish): Observable<out Effect> {
            return when (action) {
                Wish.Load -> {
                    if (state.status == State.Status.IDLE) {
                        Observable.fromCallable {
                            calculatorUseCase.calculate(percent)
                        }
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .map { ResultSuccess(it) }
                            .cast(Effect::class.java)
                            .startWith(StartedLoading)
                            .onErrorReturn { ResultError(it) }
                    } else {
                        Observable.empty()
                    }
                }
            }
        }

    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is StartedLoading -> state.copy(
                status = State.Status.LOADING
            )
            is ResultSuccess -> state.copy(
                status = State.Status.DATA,
                accrual = effect.actuals
            )
            is ResultError -> state.copy(
                status = State.Status.ERROR
            )
        }
    }


}