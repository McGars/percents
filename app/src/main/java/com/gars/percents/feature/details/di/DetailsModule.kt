package com.gars.percents.feature.details.di

import com.badoo.binder.lifecycle.Lifecycle
import com.gars.percents.common.FragmentsBindings
import com.gars.percents.details.damain.CalculatorUseCase
import com.gars.percents.details.di.DetailsScopeComponent
import com.gars.percents.feature.details.mvi.DetailsBinder
import com.gars.percents.details.mvi.DetailsFeature
import com.gars.percents.details.presentation.view.DetailsView
import org.koin.dsl.module


val detailsModule = module {

    scope<DetailsScopeComponent> {
        scoped<FragmentsBindings<DetailsView>> { DetailsBinder(get(), get(), get()) }
        scoped<CalculatorUseCase> { CalculatorUseCase.Impl() }
        scoped { DetailsFeature(get(), get()) }
        scoped { Lifecycle.manual() }
    }

}