package com.alavpa.meep.presentation.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ThreadProvider {
    fun provideIO(): Scheduler {
        return Schedulers.io()
    }

    fun provideMain(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}