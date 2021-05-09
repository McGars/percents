package com.gars.percents.details.mvi

import com.gars.percents.details.presentation.UiDetailsEvent
import com.gars.percents.navigation.data.model.Page
import com.gars.percents.navigation.mvi.NavigatorFeature

class UiDetailsEventTransformer : (UiDetailsEvent) -> DetailsFeature.Wish? {

    override fun invoke(event: UiDetailsEvent): DetailsFeature.Wish? {
        return when (event) {
            UiDetailsEvent.Ready -> DetailsFeature.Wish.Load
            else -> null
        }

    }

}