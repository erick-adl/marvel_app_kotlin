package com.erick.marvel.data.character

import com.erick.marvel.domain.character.CharacterApi
import com.erick.marvel.domain.character.SearchCharactersItems
import com.erick.marvel.domain.repository.RxReadableDataSource
import io.reactivex.Observable

class SearchCharactersApiDataSource(
        private val characterApi: CharacterApi
) : RxReadableDataSource<String, SearchCharactersItems> {

    override fun getByKey(key: String): Observable<SearchCharactersItems> =
            characterApi.searchForCharacter(key, 0)

    override fun getAll(): Observable<List<SearchCharactersItems>> =
            Observable.empty()
}