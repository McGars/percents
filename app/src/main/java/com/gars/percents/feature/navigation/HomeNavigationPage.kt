package com.gars.percents.feature.navigation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.gars.percents.R
import com.gars.percents.home.HomeFragment
import com.gars.percents.navigation.data.model.NavigationPage
import com.gars.percents.navigation.data.model.Page


class HomeNavigationPage : NavigationPage {
    override val page: Page = Page.Home

    override fun navigate(fragmentManager: FragmentManager, vararg params: Any) {

        var count = fragmentManager.backStackEntryCount
        if (count == 0) {
            fragmentManager.commit {
                replace(R.id.container, HomeFragment.newInstance(), "root")
            }
        } else{
            while (count > 1) {
                count--
                fragmentManager.popBackStack()
            }
        }
    }

}