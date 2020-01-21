package com.erick.marvel.presentation.characterlist

import com.erick.marvel.base.BaseView
import com.erick.marvel.domain.character.CharacterItem
import com.erick.marvel.domain.character.CharacterItemComics

interface CharacterListView : BaseView {
    fun showCharacterList(characterItems: List<CharacterUI>)
    fun showSearchingError()
    fun showLoadingSpinner()
    fun hideLoadingSpinner()
}

interface CharacterUI

data class CharacterItemUI(
        val id: Int,
        val name: String,
        val imageUrl: String?,
        val description: String,
        val comics: CharacterUIComics?
) : CharacterUI

data class CharacterUIComics(
        val available: Int?,
        val collectionURI: String?
)
    val list = mutableListOf<CharacterItemUI>()

fun List<CharacterItem>.toUI(): List<CharacterItemUI> {
    list += map { it.toUI() }
    return list
}

fun CharacterItem.toUI(): CharacterItemUI {

    val descriptionString = if (description == null || description != "")
        description!!
    else
        "Sorry, but there's no description available for this character" //TODO: Use TextWrapper with resId

    return CharacterItemUI(
            id = id,
            name = name,
            imageUrl = imageUrl,
            description = descriptionString,
            comics = comics?.toUI()
    )
}

fun CharacterItemComics.toUI(): CharacterUIComics {
    return CharacterUIComics(
            available = available,
            collectionURI = collectionURI
    )
}