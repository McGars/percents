package com.gars.percents.home.mvi

import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ReducerFeature
import com.gars.percents.home.data.Percent
import com.gars.percents.home.mvi.HomeFeature.*


class HomeFeature : ReducerFeature<Wish, State, Nothing>(
    initialState = State(),
    reducer = ReducerImpl()
) {

    // Define immutable state
    data class State(
        val percent: Float = 10f,
        val deposit: Float = 0f,
        val monthAdd: Int = 0,
        val monthAddBreak: Int = 0,
        val years: Int = 1,
        val portion: Int = 1,
        val takeOff: Float = 0f,
        val takeOffEndMonth: Int = 0,
        val takeOffCount: Float = 0f
    )

    // Define the ways it could be affected
    sealed class Wish {
        class PercentsUpdate(val value: Float) : Wish()
        class DepositUpdate(val value: Float) : Wish()
        class MonthAddUpdate(val value: Int) : Wish()
        class MonthAddBreakUpdate(val value: Int) : Wish()
        class YearsUpdate(val value: Int) : Wish()
        class PortionUpdate(val value: Int) : Wish()
        class TakeOffUpdate(val value: Float) : Wish()
        class TakeOffEndMonthUpdate(val value: Int) : Wish()
        class TakeOffCountUpdate(val value: Float) : Wish()
    }

    // Define your reducer
    class ReducerImpl : Reducer<State, Wish> {

        override fun invoke(state: State, wish: Wish): State =
            when (wish) {
                is Wish.PercentsUpdate -> state.copy(percent = wish.value)
                is Wish.DepositUpdate -> state.copy(deposit = wish.value)
                is Wish.MonthAddUpdate -> state.copy(monthAdd = wish.value)
                is Wish.MonthAddBreakUpdate -> state.copy(monthAddBreak = wish.value)
                is Wish.YearsUpdate -> state.copy(years = wish.value)
                is Wish.PortionUpdate -> state.copy(portion = wish.value)
                is Wish.TakeOffUpdate -> state.copy(takeOff = wish.value)
                is Wish.TakeOffEndMonthUpdate -> state.copy(takeOffEndMonth = wish.value)
                is Wish.TakeOffCountUpdate -> state.copy(takeOffCount = wish.value)
            }
    }

}