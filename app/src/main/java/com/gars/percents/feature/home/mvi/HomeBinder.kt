package com.gars.percents.feature.home.mvi

import com.badoo.binder.lifecycle.ManualLifecycle
import com.badoo.binder.using
import com.gars.percents.common.FragmentsBindings
import com.gars.percents.home.mvi.HomeFeature
import com.gars.percents.home.mvi.UiHomeEventTransformer
import com.gars.percents.feature.home.mvi.transformer.UiHomeNavigationEventTransformer
import com.gars.percents.home.presentation.HomeView
import com.gars.percents.navigation.mvi.NavigatorFeature
import io.reactivex.Observable
import io.reactivex.ObservableSource


class HomeBinder(
    lifecycleOwner: ManualLifecycle,
    private val homeFeature: HomeFeature,
    private val navigatorFeature: NavigatorFeature,
): FragmentsBindings<HomeView>(lifecycleOwner) {

    override fun setup(view: HomeView) {
        binder.bind(view to homeFeature using UiHomeEventTransformer())
        binder.bind(combineLatest(view, homeFeature) to navigatorFeature using UiHomeNavigationEventTransformer())
    }

    private fun <T1, T2> combineLatest(o1: ObservableSource<T1>, o2: ObservableSource<T2>): ObservableSource<Pair<T1, T2>> =
        Observable.combineLatest(o1, o2, { t1: T1, t2: T2 -> t1 to t2})

}