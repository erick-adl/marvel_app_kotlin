package com.erick.marvel.presentation.characterdetail

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.pedrogomez.renderers.ListAdapteeCollection
import com.pedrogomez.renderers.RVRendererAdapter
import com.pedrogomez.renderers.RendererBuilder
import com.erick.marvel.MarvelApplication
import com.erick.marvel.R
import com.erick.marvel.base.BaseActivity
import com.erick.marvel.lifecycle.Presenter
import com.erick.marvel.presentation.characterdetail.adapter.ComicItemRenderer
import com.erick.marvel.presentation.characterdetail.injector.CharacterDetailModule
import com.erick.marvel.presentation.characterdetail.injector.DaggerCharacterDetailComponent
import com.erick.marvel.presentation.characterlist.CharacterItemUI
import com.erick.marvel.presentation.characterlist.CharacterListPresenter.Companion.ID_NOT_FOUND
import com.erick.marvel.presentation.navigator.IntentExtras
import com.erick.marvel.presentation.utils.gone
import com.erick.marvel.presentation.utils.loadImage
import com.erick.marvel.presentation.utils.visible
import kotlinx.android.synthetic.main.characterdetail_layout.*
import javax.inject.Inject

class CharacterDetailActivity : BaseActivity(), CharacterDetailView {

    override val layoutRes: Int = R.layout.characterdetail_layout

    lateinit var comicsAdapter: RVRendererAdapter<ComicUI>

    @Presenter
    @Inject
    lateinit var presenter: CharacterDetailPresenter

    override fun initInjector() {
        DaggerCharacterDetailComponent.builder()
                .applicationComponent(MarvelApplication.applicationComponent)
                .characterDetailModule(CharacterDetailModule(this))
                .build()
                .inject(this)
    }

    override fun setupViews() {
        presenter.characterId = intent.getIntExtra(IntentExtras.CHARACTER_ID, ID_NOT_FOUND)
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
    }

    override fun showCharacterDetails(characterDetails: CharacterItemUI) {
        characterImage.loadImage(characterDetails.imageUrl)
        characterName.text = characterDetails.name
        description.text = characterDetails.description
        comicsAvailable.text = characterDetails.comics?.available.toString()
    }

    override fun showComicList(comicItems: List<ComicUI>) {
        if (comicItems.hasItems()) {
            comicList.visible()
            noComicsAvailable.gone()
            comicsAdapter.diffUpdate(comicItems)
        } else {
            comicList.gone()
            noComicsAvailable.visible()
        }
    }

    private fun List<ComicUI>.hasItems(): Boolean {
        return size > 0
    }

    private fun setupToolbar() {
        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener { presenter.onBackPressed() }
    }

    private fun setupAdapter() {
        val rendererBuilder = RendererBuilder<ComicUI>()
                .bind(ComicItemUI::class.java, ComicItemRenderer({ presenter.onComicPressed(it) }))
        comicsAdapter = RVRendererAdapter(rendererBuilder, ListAdapteeCollection())
    }

    private fun setupRecyclerView() {
        comicList.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        comicList.setHasFixedSize(true)
        comicList.adapter = comicsAdapter
    }
}