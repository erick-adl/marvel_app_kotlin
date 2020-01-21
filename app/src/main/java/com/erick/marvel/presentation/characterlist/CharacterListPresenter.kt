package com.erick.marvel.presentation.characterlist

import com.erick.marvel.base.BasePresenter
import com.erick.marvel.domain.character.CharacterItem
import com.erick.marvel.domain.character.SearchForCharactersUseCase
import com.erick.marvel.domain.reactive.addDisposableTo
import com.erick.marvel.presentation.navigator.ActivityNavigator
import io.reactivex.rxkotlin.subscribeBy

class CharacterListPresenter(
        private val searchForCharactersUseCase: SearchForCharactersUseCase,
        private val activityNavigator: ActivityNavigator
) : BasePresenter<CharacterListView>() {

    val list = mutableListOf<CharacterItem>()

    override fun onCreate() {
        super.onCreate()
        getView()?.showLoadingSpinner()
        onSearchChanged("")
    }

    fun onCharacterPressed(character: CharacterItemUI) {
        activityNavigator.goToCharacterDetail(character.id)
    }

    fun onSearchChanged(search: String) {
        searchForCharactersUseCase.execute(search).subscribeBy(


                onNext = {
                    getView()?.hideLoadingSpinner()
                    getView()?.showCharacterList(it.toUI())
                },
                onError = {
                    getView()?.hideLoadingSpinner()
                    getView()?.showSearchingError()
                }
        ).addDisposableTo(disposeBag)
    }

    companion object {
        const val ID_NOT_FOUND = -1
    }
}