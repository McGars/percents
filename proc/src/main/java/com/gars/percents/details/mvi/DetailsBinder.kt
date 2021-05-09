package com.gars.percents.details.mvi

import com.badoo.binder.lifecycle.ManualLifecycle
import com.badoo.binder.using
import com.gars.percents.base.FragmentsBindings2
import com.gars.percents.details.presentation.DetailsView
import com.gars.percents.navigation.mvi.NavigatorFeature


class DetailsBinder(
    lifecycle: ManualLifecycle,
    private val detailsFeature: DetailsFeature,
    private val navigatorFeature: NavigatorFeature,
): FragmentsBindings2<DetailsView>(lifecycle) {

    override fun setup(view: DetailsView) {
        binder.bind(view to detailsFeature using UiDetailsEventTransformer())
        binder.bind(detailsFeature to view using DetailsFeatureToDetailsViewModelTransformer())
        binder.bind(view to navigatorFeature using UiDetailsNavigationEventTransformer())
    }

}