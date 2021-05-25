package com.gars.percents.home.di

import com.gars.percents.common.FragmentsBindings
import com.gars.percents.home.presentation.HomeView
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope


class HomeScopeComponent : KoinScopeComponent {

    override val scope: Scope by getOrCreateScope()

    val binder: FragmentsBindings<HomeView> by inject()

}