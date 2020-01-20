package com.erick.marvel.injection.modules

import com.erick.marvel.data.comic.ComicApiClient
import com.erick.marvel.domain.comic.ComicApi
import com.erick.marvel.domain.comic.ComicResource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ComicModule {

    @Provides
    @Singleton
    fun provideComicResource(
            characterApi: ComicApi
    ): ComicResource {
        return ComicResource(characterApi)
    }

    @Provides
    @Singleton
    fun provideComicApi(): ComicApi {
        return ComicApiClient()
    }
}