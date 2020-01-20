package com.erick.marvel.data.adapter


import com.erick.marvel.data.exception.MarvelServerExceptionFactory
import io.reactivex.Single
import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class RxSingleErrorCallAdapter constructor(private val wrapped: CallAdapter<Any, Single<*>>) : CallAdapter<Any, Single<*>> {
    override fun responseType(): Type {
        return wrapped.responseType()
    }

    @SuppressWarnings("unchecked")
    override fun adapt(call: Call<Any>): Single<*> {
        return wrapped.adapt(call)
                .onErrorResumeNext(Function { throwable ->
                    Single.error(MarvelServerExceptionFactory.create(throwable))
                })
    }
}