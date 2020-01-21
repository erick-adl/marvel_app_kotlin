package com.erick.marvel.domain.character

import io.reactivex.Observable

interface CharacterApi {
    fun searchForCharacter(searchText: String, offset: Int): Observable<SearchCharactersItems>
}