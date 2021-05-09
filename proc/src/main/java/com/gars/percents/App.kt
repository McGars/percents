package com.gars.percents

import android.app.Application
import com.gars.percents.details.di.detailsModule
import com.gars.percents.di.appModule
import com.gars.percents.di.routerModule
import com.gars.percents.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        applyLogger()

        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                detailsModule,
                homeModule,
                routerModule
            )
        }

    }

    private fun applyLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

}