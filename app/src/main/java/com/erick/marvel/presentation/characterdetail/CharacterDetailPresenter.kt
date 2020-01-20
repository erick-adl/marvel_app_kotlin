package com.erick.marvel.presentation.characterdetail

import com.erick.marvel.base.BasePresenter
import com.erick.marvel.domain.character.GetCharacterFromIdUseCase
import com.erick.marvel.domain.comic.GetComicsFromCharacterIdUseCase
import com.erick.marvel.domain.logger.Logger
import com.erick.marvel.presentation.characterlist.toUI
import com.erick.marvel.presentation.navigator.ActivityNavigator
import io.reactivex.rxkotlin.subscribeBy

class CharacterDetailPresenter(
        private val getCharacterFromIdUseCase: GetCharacterFromIdUseCase,
        private val getComicsFromCharacterIdUseCase: GetComicsFromCharacterIdUseCase,
        private val activityNavigator: ActivityNavigator
) : BasePresenter<CharacterDetailView>() {

    var characterId: Int? = null
        set(value) {
            field = value
            field?.let {
                getCharacterDetails()
                getComics()
            }
        }

    fun getCharacterDetails() {
        getCharacterFromIdUseCase.execute(characterId!!).subscribeBy(
                onNext = { getView()?.showCharacterDetails(it.toUI()) }
        )
    }

    fun getComics() {
        getComicsFromCharacterIdUseCase.execute(characterId!!).subscribeBy(
                onNext = { getView()?.showComicList(it.toUI())}
        )
    }

    fun onComicPressed(comic: ComicItemUI) {

    }

    fun onBackPressed() {
        activityNavigator.closeDetail()
    }
}