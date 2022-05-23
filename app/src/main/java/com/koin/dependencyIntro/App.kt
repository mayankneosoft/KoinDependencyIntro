package com.koin.dependencyIntro

import android.app.Application
import com.koin.dependencyIntro.module.appModule
import com.koin.dependencyIntro.module.repositoryModule
import com.koin.dependencyIntro.module.viewModelModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules (listOf(appModule, repositoryModule, viewModelModule))
        }
    }
}