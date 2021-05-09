package com.gars.percents.di

import com.gars.percents.navigation.DetailsNavigationPage
import com.gars.percents.navigation.HomeNavigationPage
import com.gars.percents.navigation.Navigator
import com.gars.percents.navigation.mvi.NavigatorFeature
import org.koin.dsl.module


val routerModule = module {
    single { Navigator(get(), get()) }
    single { NavigatorFeature(get()) }
    single {
        listOf(
            HomeNavigationPage(),
            DetailsNavigationPage(),
        )
    }
}