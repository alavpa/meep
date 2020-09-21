package com.alavpa.meep.presentation.base

import androidx.lifecycle.ViewModel
import com.alavpa.meep.presentation.utils.ThreadProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

open class Presenter : KoinComponent, ViewModel() {
    private val compositeDisposable: CompositeDisposable by inject()
    private val threadProvider: ThreadProvider by inject()
    fun <T> Single<T>.exec(
        onError: (Throwable) -> Unit = { it.printStackTrace() },
        onSuccess: (T) -> Unit
    ) {
        this.subscribeOn(threadProvider.provideIO())
            .observeOn(threadProvider.provideMain())
            .subscribe(onSuccess, onError)
            .also { compositeDisposable.add(it) }
    }

    fun destroy() {
        compositeDisposable.clear()
    }
}