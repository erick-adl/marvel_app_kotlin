package com.erick.marvel.injection.modules

import com.erick.marvel.domain.repository.ApplicationTimeProvider
import com.erick.marvel.domain.repository.TimeProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
open class TimeProviderModule {

    @Provides
    @Reusable
    open fun providesTimeProvider(): TimeProvider = ApplicationTimeProvider()
}