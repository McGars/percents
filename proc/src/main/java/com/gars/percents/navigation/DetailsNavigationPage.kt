package com.gars.percents.navigation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.gars.percents.R
import com.gars.percents.common.getOrCreateScope
import com.gars.percents.common.koin
import com.gars.percents.details.DetailsFragment
import com.gars.percents.details.di.DetailsScopeComponent
import com.gars.percents.navigation.data.model.NavigationPage
import com.gars.percents.navigation.data.model.Page
import org.koin.core.parameter.parametersOf
import timber.log.Timber
import java.util.logging.*


class DetailsNavigationPage : NavigationPage {
    override val page: Page = Page.Details

    override fun navigate(fragmentManager: FragmentManager, vararg params: Any) {
        val scope = koin.getOrCreateScope<DetailsScopeComponent>()
        scope.addParameters(parametersOf(*params))

        Timber.d("DetailsNavigationPage")

        fragmentManager.commit {
            replace(R.id.container, DetailsFragment.newInstance())
            addToBackStack(null)
        }
    }

}