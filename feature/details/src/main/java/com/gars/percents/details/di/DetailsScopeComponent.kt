package com.gars.percents.details.di

import com.gars.percents.common.FragmentsBindings
import com.gars.percents.common.StringResources
import com.gars.percents.details.presentation.view.DetailsView
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope


class DetailsScopeComponent : KoinScopeComponent {

    override val scope: Scope by getOrCreateScope()

    val binder: FragmentsBindings<DetailsView> by inject()

    val stringResources: StringResources by inject()
}