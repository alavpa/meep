package com.alavpa.meep.presentation

import com.alavpa.meep.presentation.main.MainPresenter
import com.alavpa.meep.presentation.utils.ThreadProvider
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single { ThreadProvider() }
    factory { CompositeDisposable() }
    viewModel { MainPresenter(get()) }
}