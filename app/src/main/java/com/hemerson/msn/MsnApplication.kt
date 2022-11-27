package com.hemerson.msn

import android.app.Application
import android.content.Context
import com.hemerson.msn.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MsnApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MsnApplication)
            modules(allModules)
        }
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}