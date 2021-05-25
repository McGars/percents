package com.gars.percents.feature.details.mvi.transformer

import com.gars.percents.details.presentation.event.UiDetailsEvent
import com.gars.percents.navigation.data.model.Page
import com.gars.percents.navigation.mvi.NavigatorFeature

class UiDetailsNavigationEventTransformer : (UiDetailsEvent) -> NavigatorFeature.Wish? {

    override fun invoke(event: UiDetailsEvent): NavigatorFeature.Wish? {
        return when (event) {
            UiDetailsEvent.BackToHome -> NavigatorFeature.Wish.SelectPage(Page.Home)
            else -> null
        }

    }

}