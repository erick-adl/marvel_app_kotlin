package com.erick.marvel.presentation.characterdetail.injector

import android.support.v7.app.AppCompatActivity
import com.erick.marvel.domain.character.CharacterResource
import com.erick.marvel.domain.character.GetCharacterFromId
import com.erick.marvel.domain.character.GetCharacterFromIdUseCase
import com.erick.marvel.domain.comic.ComicResource
import com.erick.marvel.domain.comic.GetComicsFromCharacterId
import com.erick.marvel.domain.comic.GetComicsFromCharacterIdUseCase
import com.erick.marvel.domain.executor.ThreadScheduler
import com.erick.marvel.injection.PerActivity
import com.erick.marvel.injection.components.ActivityComponent
import com.erick.marvel.injection.components.ApplicationComponent
import com.erick.marvel.injection.modules.ActivityModule
import com.erick.marvel.presentation.characterdetail.CharacterDetailActivity
import com.erick.marvel.presentation.characterdetail.CharacterDetailPresenter
import com.erick.marvel.injection.modules.MarvelModule
import com.erick.marvel.presentation.navigator.ActivityNavigator
import com.erick.marvel.presentation.navigator.ApplicationActivityNavigator
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailModule(private val activity: AppCompatActivity) : ActivityModule {

    @Provides
    fun provideCharacterDetailPresenter(
            getCharacterFromIdUseCase: GetCharacterFromIdUseCase,
            getComicsFromCharacterIdUseCase: GetComicsFromCharacterIdUseCase,
            activityNavigator: ActivityNavigator
    ): CharacterDetailPresenter {
        return CharacterDetailPresenter(
                getCharacterFromIdUseCase,
                getComicsFromCharacterIdUseCase,
                activityNavigator
        )
    }

    @Provides
    fun provideGetCharacterFromIdUseCase(
            characterResource: CharacterResource,
            threadScheduler: ThreadScheduler
    ): GetCharacterFromIdUseCase {
        return GetCharacterFromId(
                characterResource = characterResource,
                threadScheduler = threadScheduler
        )
    }

    @Provides
    fun provideGetComicsFromCharacterIdUseCase(
            comicResource: ComicResource,
            threadScheduler: ThreadScheduler
    ): GetComicsFromCharacterIdUseCase {
        return GetComicsFromCharacterId(
                comicResource = comicResource,
                threadScheduler = threadScheduler
        )
    }

    @Provides
    fun provideActivityNavigator(): ActivityNavigator = ApplicationActivityNavigator(
            activity
    )
}

@PerActivity
@Component(
        dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(CharacterDetailModule::class)
)
interface CharacterDetailComponent : ActivityComponent {
    fun inject(activity: CharacterDetailActivity)
}