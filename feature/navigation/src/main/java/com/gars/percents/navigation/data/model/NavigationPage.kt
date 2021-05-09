package com.gars.percents.navigation.data.model

import androidx.fragment.app.FragmentManager


interface NavigationPage {
    val page: Page
    fun navigate(fragmentManager: FragmentManager, vararg params: Any)
}