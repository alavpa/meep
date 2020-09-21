package com.alavpa.meep.domain.interactors

import io.reactivex.Single

interface Interactor<T> {
    fun build(): Single<T>
}