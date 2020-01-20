package com.erick.marvel.injection.components

import com.erick.marvel.MarvelApplication
import com.erick.marvel.domain.character.CharacterResource
import com.erick.marvel.domain.comic.ComicResource
import com.erick.marvel.domain.executor.PostExecutionThread
import com.erick.marvel.domain.executor.ThreadExecutor
import com.erick.marvel.domain.executor.ThreadScheduler
import com.erick.marvel.injection.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: MarvelApplication)

    fun threadScheduler(): ThreadScheduler

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    fun characterResource(): CharacterResource

    fun comicResource(): ComicResource
}