package com.gars.percents.details.di

import com.badoo.binder.lifecycle.Lifecycle
import com.gars.percents.details.domain.CalculatorUseCase
import com.gars.percents.details.mvi.DetailsBinder
import com.gars.percents.details.mvi.DetailsFeature
import org.koin.dsl.module


val detailsModule = module {

    scope<DetailsScopeComponent> {
        scoped { DetailsBinder(get(), get(), get()) }
        scoped<CalculatorUseCase> { CalculatorUseCase.Impl() }
        scoped { params -> DetailsFeature(get(), get()) }
        scoped { Lifecycle.manual() }
    }

}