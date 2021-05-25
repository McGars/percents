package com.gars.percents.feature.details.mvi

import com.badoo.binder.lifecycle.ManualLifecycle
import com.badoo.binder.using
import com.gars.percents.common.FragmentsBindings
import com.gars.percents.details.mvi.DetailsFeature
import com.gars.percents.details.mvi.transformer.DetailsFeatureToDetailsViewModelTransformer
import com.gars.percents.details.mvi.transformer.UiDetailsEventTransformer
import com.gars.percents.feature.details.mvi.transformer.UiDetailsNavigationEventTransformer
import com.gars.percents.details.presentation.view.DetailsView
import com.gars.percents.navigation.mvi.NavigatorFeature


class DetailsBinder(
    lifecycle: ManualLifecycle,
    private val detailsFeature: DetailsFeature,
    private val navigatorFeature: NavigatorFeature,
): FragmentsBindings<DetailsView>(lifecycle) {

    override fun setup(view: DetailsView) {
        binder.bind(view to detailsFeature using UiDetailsEventTransformer())
        binder.bind(detailsFeature to view using DetailsFeatureToDetailsViewModelTransformer())
        binder.bind(view to navigatorFeature using UiDetailsNavigationEventTransformer())
    }

}