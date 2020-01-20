package com.erick.marvel.injection.modules

import com.erick.marvel.data.character.CharacterApiClient
import com.erick.marvel.domain.character.CharacterApi
import dagger.Module
import dagger.Provides

@Module
class MarvelModule {

    @Provides
    fun provideCharacterApi(): CharacterApi {
        return CharacterApiClient()
    }
}