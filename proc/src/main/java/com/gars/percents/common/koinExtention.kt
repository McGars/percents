package com.gars.percents.common

import org.koin.core.Koin
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getScopeId
import org.koin.java.KoinJavaComponent


val koin: Koin get() = KoinJavaComponent.getKoin()

inline fun <reified T : KoinScopeComponent> Koin.getOrCreateScope() =
    getOrCreateScope<T>(T::class.getScopeId())