package com.gars.percents.home.mvi

import com.gars.percents.common.getOrCreateScope
import com.gars.percents.common.koin
import com.gars.percents.details.di.DetailsScopeComponent
import com.gars.percents.home.data.Percent
import com.gars.percents.home.presentation.UiHomeEvent
import com.gars.percents.navigation.data.model.Page
import com.gars.percents.navigation.mvi.NavigatorFeature
import timber.log.Timber

class UiHomeNavigationEventTransformer :
        (Pair<UiHomeEvent, HomeFeature.State>) -> NavigatorFeature.Wish? {

    override fun invoke(p1: Pair<UiHomeEvent, HomeFeature.State>): NavigatorFeature.Wish? {
        val (event, state) = p1
        Timber.d("UiHomeNavigationEventTransformer")
        return when (event) {
            UiHomeEvent.Calculate -> {
                val percent = convert(state)
                val scope = koin.getOrCreateScope<DetailsScopeComponent>()
                scope.declare(percent, override = true)

                NavigatorFeature.Wish.SelectPage(Page.Details, listOf(percent))
            }
            else -> null
        }

    }

    private fun convert(state: HomeFeature.State): Percent =
        Percent(
            percent = state.percent,
            deposit = state.deposit,
            monthAdd = state.monthAdd,
            monthAddBreak = state.monthAddBreak,
            years = state.years,
            portion = state.portion,
            takeOff = state.takeOff,
            takeOffCount = state.takeOffCount,
            takeOffEndMonth = state.takeOffEndMonth
        )
}