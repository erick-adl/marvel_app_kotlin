package com.erick.marvel.presentation.characterdetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrogomez.renderers.Renderer
import com.erick.marvel.R
import com.erick.marvel.presentation.characterdetail.ComicItemUI
import com.erick.marvel.presentation.utils.loadImage
import kotlinx.android.synthetic.main.comiclist_item.view.*

class ComicItemRenderer(
        private val onClick: (item: ComicItemUI) -> Unit = {}
) : Renderer<ComicItemUI>() {

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View =
            inflater.inflate(R.layout.comiclist_item, parent, false)

    override fun setUpView(rootView: View) {}

    override fun hookListeners(rootView: View) {
        rootView.setOnClickListener {
            onClick.invoke(content)
        }
    }

    override fun render() {
        with(rootView) {
            cover.loadImage(content.imageUrl)
        }
    }
}