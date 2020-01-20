package com.erick.marvel.injection.components

import com.erick.marvel.injection.PerActivity
import com.erick.marvel.injection.modules.ApplicationModule
import dagger.Component

@PerActivity
@Component(
        dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(
                ApplicationModule::class
        ))
interface ActivityComponent {
}