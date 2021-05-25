package com.gars.percents.details.mvi.transformer

import com.gars.percents.details.mvi.DetailsFeature
import com.gars.percents.details.presentation.event.UiDetailsEvent

class UiDetailsEventTransformer : (UiDetailsEvent) -> DetailsFeature.Wish? {

    override fun invoke(event: UiDetailsEvent): DetailsFeature.Wish? {
        return when (event) {
            UiDetailsEvent.Ready -> DetailsFeature.Wish.Load
            else -> null
        }

    }

}