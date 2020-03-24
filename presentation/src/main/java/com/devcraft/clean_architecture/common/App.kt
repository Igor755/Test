package com.devcraft.clean_architecture.common

import android.app.Application
import com.devcraft.clean_architecture.di.adaptersModule
import com.devcraft.clean_architecture.di.interactorsModule
import com.devcraft.clean_architecture.di.viewModelsModule
import com.devcraft.di.dataProvidersModule
import com.devcraft.di.repositoriesModule
import com.devcraft.di.sourcesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    sourcesModule,
                    dataProvidersModule,
                    adaptersModule,
                    viewModelsModule,
                    interactorsModule,
                    repositoriesModule
                )
            )
        }
    }
}