package com.erick.marvel.data.character

import com.erick.marvel.data.BuildConfig
import com.erick.marvel.data.client.ApiClientBuilder
import com.erick.marvel.data.client.MarvelApiClient
import com.erick.marvel.data.utils.getHash
import com.erick.marvel.domain.character.CharacterApi
import com.erick.marvel.domain.character.CharacterItem
import com.erick.marvel.domain.character.SearchCharactersItems
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

class CharacterApiClient : CharacterApi {

    override fun searchForCharacter(searchText: String): Observable<SearchCharactersItems> {

        val marvelApiClient = ApiClientBuilder(
                MarvelApiClient(emptySet())
        ).buildEndpoint(CharactersApiDefinition::class)

        val publicAPIKey = BuildConfig.PublicApiKey
        val timestamp = java.lang.Long.toString(System.currentTimeMillis() / 1000)
        val hash = getHash(timestamp)
        val nameStartsWith = if (searchText == "") null else searchText

        return marvelApiClient.getCharacters(
                publicKey = publicAPIKey,
                timestamp = timestamp,
                hash = hash,
                nameStartsWith = nameStartsWith
        ).map { it.toDomain(searchText) }
    }
}

interface CharactersApiDefinition {

    @GET("/v1/public/characters")
    fun getCharacters(
            @Query("apikey") publicKey: String,
            @Query("hash") hash: String,
            @Query("ts") timestamp: String,
            @Query("nameStartsWith") nameStartsWith: String?
    ): Observable<SearchCharactersApiModel>
}