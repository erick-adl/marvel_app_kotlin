package com.erick.marvel.injection.modules

import com.erick.marvel.domain.executor.PostExecutionThread
import com.erick.marvel.domain.executor.ThreadExecutor
import com.erick.marvel.domain.executor.ThreadScheduler
import com.erick.marvel.executor.DefaultThreadScheduler
import com.erick.marvel.executor.JobExecutor
import com.erick.marvel.executor.UIPostExecutionThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ExecutorsModule {

    @Provides
    @Singleton
    open fun providesThreadExecutor(): ThreadExecutor = JobExecutor()

    @Provides
    @Singleton
    open fun providesPostExecutionThread(): PostExecutionThread = UIPostExecutionThread()

    @Provides
    @Singleton
    open fun providesThreadScheduler(
            threadExecutor: ThreadExecutor,
            postExecutionThread: PostExecutionThread
    ): ThreadScheduler {
        return DefaultThreadScheduler(
                threadExecutor = threadExecutor,
                postExecutionThread = postExecutionThread
        )
    }
}