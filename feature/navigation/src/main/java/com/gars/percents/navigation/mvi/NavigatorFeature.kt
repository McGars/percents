package com.gars.percents.navigation.mvi

import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ReducerFeature
import com.gars.percents.navigation.Navigator
import com.gars.percents.navigation.data.model.Page
import com.gars.percents.navigation.mvi.NavigatorFeature.*


class NavigatorFeature(
    navigator: Navigator
) : ReducerFeature<Wish, State, Nothing>(
    initialState = State(Page.Idle, listOf()),
    reducer = ReducerImpl(navigator)
) {

    data class State(
        val page: Page,
        val params: List<Any>
    )

    sealed class Wish {
        class SelectPage(val page: Page, val params: List<Any> = emptyList()): Wish()
    }

    class ReducerImpl(private val navigator: Navigator) : Reducer<State, Wish> {
        override fun invoke(state: State, effect: Wish): State {
            return when(effect) {
                is Wish.SelectPage -> {
                    State(effect.page, effect.params).also(navigator::accept)
                }
            }
        }

    }


}