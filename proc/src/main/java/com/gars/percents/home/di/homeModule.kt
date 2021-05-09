package com.gars.percents.home.di

import com.gars.percents.home.mvi.HomeBinder
import com.gars.percents.home.mvi.HomeFeature
import org.koin.dsl.module


val homeModule = module {
    scope<HomeScopeComponent> {
        scoped { params -> HomeBinder(get(), homeFeature = get(), get()) }
        scoped { HomeFeature() }
    }
}