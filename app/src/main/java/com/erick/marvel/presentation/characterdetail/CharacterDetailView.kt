package com.erick.marvel.presentation.characterdetail

import com.erick.marvel.base.BaseView
import com.erick.marvel.domain.comic.ComicItem
import com.erick.marvel.domain.logger.Logger
import com.erick.marvel.presentation.characterlist.CharacterItemUI

interface CharacterDetailView : BaseView {
    fun showCharacterDetails(characterDetails: CharacterItemUI)
    fun showComicList(comicItems: List<ComicUI>)
}

interface ComicUI

data class ComicItemUI(
        val title: String,
        val imageUrl: String?
) : ComicUI

fun List<ComicItem>.toUI(): List<ComicItemUI> {
    return map { it.toUI() }
}

fun ComicItem.toUI(): ComicItemUI {
    return ComicItemUI(
            title = title,
            imageUrl = imageUrl
    )
}
