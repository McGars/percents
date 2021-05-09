package com.gars.percents.navigation

import androidx.fragment.app.FragmentManager
import com.gars.percents.navigation.data.model.NavigationPage
import com.gars.percents.navigation.mvi.NavigatorFeature
import io.reactivex.functions.Consumer


class Navigator(
    private val fragmentManager: FragmentManager,
    private val pages: List<NavigationPage>
): Consumer<NavigatorFeature.State> {

    override fun accept(state: NavigatorFeature.State) {
        val navigationPage = pages.first { it.page == state.page }
        navigationPage.navigate(fragmentManager, state.params)
    }

}