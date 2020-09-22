package com.alavpa.meep.ui

import androidx.multidex.MultiDexApplication
import com.alavpa.meep.androidModule
import com.alavpa.meep.data.dataModule
import com.alavpa.meep.domain.domainModule
import com.alavpa.meep.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MeepApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MeepApplication)
            modules(
                androidModule,
                domainModule,
                presentationModule,
                dataModule
            )
        }
    }
}