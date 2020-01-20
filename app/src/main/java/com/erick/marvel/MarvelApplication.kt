package com.erick.marvel

import android.app.Application
import com.erick.marvel.domain.logger.Kog
import com.erick.marvel.domain.logger.Logger
import com.erick.marvel.injection.components.ApplicationComponent
import com.erick.marvel.injection.components.DaggerApplicationComponent
import com.erick.marvel.injection.modules.ApplicationModule
import com.erick.marvel.logger.AndroidLogger

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MarvelApplication.application = this
        initLogger()
    }
    private fun initLogger() {
        Kog.plant(AndroidLogger())
        Logger.i({ "Logger planted" })
    }

    companion object {
        private lateinit var application: MarvelApplication
        val applicationComponent: ApplicationComponent by lazy {
            val appComponent = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(application))
                    .build()
            appComponent.inject(application)
            appComponent
        }
    }
}