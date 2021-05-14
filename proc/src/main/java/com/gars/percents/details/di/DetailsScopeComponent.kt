package com.gars.percents.details.di

import com.gars.percents.common.StringResources
import com.gars.percents.details.mvi.DetailsBinder
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope


class DetailsScopeComponent : KoinScopeComponent {

    override val scope: Scope by getOrCreateScope()

    val binder: DetailsBinder by inject()

    val stringResources: StringResources by inject()
}