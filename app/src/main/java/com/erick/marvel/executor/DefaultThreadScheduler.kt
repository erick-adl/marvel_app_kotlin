package com.erick.marvel.executor

import com.erick.marvel.domain.executor.PostExecutionThread
import com.erick.marvel.domain.executor.ThreadExecutor
import com.erick.marvel.domain.executor.ThreadScheduler
import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers

class DefaultThreadScheduler(
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread
) : ThreadScheduler {

    override fun applyCompletable(): CompletableTransformer {
        return CompletableTransformer {
            it.subscribeOn(Schedulers.from(threadExecutor))
                    .observeOn(postExecutionThread.getScheduler())
        }
    }

    override fun <T> apply(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(Schedulers.from(threadExecutor))
                    .observeOn(postExecutionThread.getScheduler())
        }
    }
}