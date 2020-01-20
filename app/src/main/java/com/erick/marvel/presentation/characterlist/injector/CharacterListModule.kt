package com.erick.marvel.presentation.characterlist.injector

import android.support.v7.app.AppCompatActivity
import com.erick.marvel.domain.character.CharacterResource
import com.erick.marvel.domain.character.SearchForCharacters
import com.erick.marvel.domain.character.SearchForCharactersUseCase
import com.erick.marvel.domain.executor.ThreadScheduler
import com.erick.marvel.injection.PerActivity
import com.erick.marvel.injection.components.ActivityComponent
import com.erick.marvel.injection.components.ApplicationComponent
import com.erick.marvel.injection.modules.ActivityModule
import com.erick.marvel.injection.modules.MarvelModule
import com.erick.marvel.presentation.characterlist.CharacterListActivity
import com.erick.marvel.presentation.characterlist.CharacterListPresenter
import com.erick.marvel.presentation.navigator.ActivityNavigator
import com.erick.marvel.presentation.navigator.ApplicationActivityNavigator
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class CharacterListModule(private val activity: AppCompatActivity) : ActivityModule {

    @Provides
    fun provideCharacterListPresenter(
            searchForCharactersUseCase: SearchForCharactersUseCase,
            activityNavigator: ActivityNavigator
    ): CharacterListPresenter {
        return CharacterListPresenter(
                searchForCharactersUseCase,
                activityNavigator
        )
    }

    @Provides
    fun provideSearchForCharactersUseCase(
            characterResource: CharacterResource,
            threadScheduler: ThreadScheduler
    ): SearchForCharactersUseCase {
        return SearchForCharacters(
                characterResource = characterResource,
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
        modules = arrayOf(CharacterListModule::class)
)
interface CharacterListComponent : ActivityComponent {
    fun inject(activity: CharacterListActivity)
}