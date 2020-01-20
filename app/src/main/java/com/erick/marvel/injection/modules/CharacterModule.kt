package com.erick.marvel.injection.modules

import com.erick.marvel.data.character.SearchCharactersApiDataSource
import com.erick.marvel.data.repository.CachePolicyTtl
import com.erick.marvel.data.repository.RxInMemoryCacheDataSource
import com.erick.marvel.domain.character.CharacterApi
import com.erick.marvel.domain.character.CharacterResource
import com.erick.marvel.domain.character.SearchCharactersItems
import com.erick.marvel.domain.repository.RxBaseRepository
import com.erick.marvel.domain.repository.TimeProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module(includes = arrayOf(TimeProviderModule::class, MarvelModule::class))
open class CharacterModule {

    companion object {
        private val REPO_VERSION: Int = 1
    }

    @Provides
    @Singleton
    fun provideCharacterResource(
            searchCharactersRepository: RxBaseRepository<String, SearchCharactersItems>
    ): CharacterResource {
        return CharacterResource(
                searchCharactersRepository = searchCharactersRepository
        )
    }

    @Provides
    @Reusable
    fun providesSearchCharactersRepository(
            searchCharactersInMemoryDataSource: RxInMemoryCacheDataSource<String, SearchCharactersItems>,
            searchCharactersApiDataSource: SearchCharactersApiDataSource
    ): RxBaseRepository<String, SearchCharactersItems> =
            RxBaseRepository(
                    rxCacheableDataSources = listOf(searchCharactersInMemoryDataSource),
                    rxWriteableDataSources = listOf(),
                    rxReadableDataSources = listOf(searchCharactersApiDataSource)
            )

    @Provides
    @Reusable
    fun providesSearchCharactersApiDataSource(
            characterApi: CharacterApi
    ): SearchCharactersApiDataSource =
            SearchCharactersApiDataSource(characterApi)

    @Provides
    @Reusable
    fun providesSearchCharactersInMemoryDataSource(
            timeProvider: TimeProvider
    ): RxInMemoryCacheDataSource<String, SearchCharactersItems> =
            RxInMemoryCacheDataSource(
                    version = REPO_VERSION,
                    timeProvider = timeProvider,
                    policies = listOf(
                            CachePolicyTtl.oneMinute(timeProvider = timeProvider)
                    )
            )

}