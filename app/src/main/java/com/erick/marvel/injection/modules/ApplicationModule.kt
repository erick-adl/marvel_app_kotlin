package com.erick.marvel.injection.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(
        ExecutorsModule::class,
        CharacterModule::class,
        ComicModule::class))
class ApplicationModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application
}