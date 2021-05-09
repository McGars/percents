package com.gars.percents.details.mvi

import com.gars.percents.details.presentation.UiDetailsEvent
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