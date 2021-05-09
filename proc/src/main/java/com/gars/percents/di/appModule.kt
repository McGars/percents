package com.gars.percents.di

import com.gars.percents.common.StringResources
import org.koin.dsl.module


val appModule = module {

    single<StringResources> { StringResources.Impl(get()) }

}