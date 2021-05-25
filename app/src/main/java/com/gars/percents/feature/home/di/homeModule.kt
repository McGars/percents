package com.gars.percents.feature.home.di

import com.badoo.binder.lifecycle.Lifecycle
import com.gars.percents.common.FragmentsBindings
import com.gars.percents.home.di.HomeScopeComponent
import com.gars.percents.feature.home.mvi.HomeBinder
import com.gars.percents.home.mvi.HomeFeature
import com.gars.percents.home.presentation.HomeView
import org.koin.dsl.module


val homeModule = module {
    scope<HomeScopeComponent> {
        scoped<FragmentsBindings<HomeView>> { HomeBinder(get(), homeFeature = get(), get()) }
        scoped { HomeFeature() }
        scoped { Lifecycle.manual() }
    }
}