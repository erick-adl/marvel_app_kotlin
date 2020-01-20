package com.erick.marvel.domain.comic

import com.erick.marvel.domain.executor.ThreadScheduler
import com.erick.marvel.domain.executor.applyScheduler
import io.reactivex.Observable

interface GetComicsFromCharacterIdUseCase {
    fun execute(characterId: Int): Observable<List<ComicItem>>
}

class GetComicsFromCharacterId(
        private val comicResource: ComicResource,
        private val threadScheduler: ThreadScheduler
) : GetComicsFromCharacterIdUseCase {
    override fun execute(characterId: Int): Observable<List<ComicItem>> {
        return comicResource.getComicsFromCharacterId(characterId)
                .applyScheduler(threadScheduler)
    }
}
