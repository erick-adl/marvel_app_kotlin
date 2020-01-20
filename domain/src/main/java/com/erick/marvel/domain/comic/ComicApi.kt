package com.erick.marvel.domain.comic

import io.reactivex.Observable

interface ComicApi {
    fun getComicsFromCharacterId(characterId: Int): Observable<List<ComicItem>>
}