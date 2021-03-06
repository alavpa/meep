package com.alavpa.meep

import com.alavpa.meep.ui.model.RenderManager
import com.alavpa.meep.ui.utils.MarkerManager
import com.alavpa.meep.ui.utils.MeepClusterManager
import org.koin.dsl.module

val androidModule = module {
    single { MarkerManager(get()) }
    single { MeepClusterManager.Provider() }
    single { RenderManager() }
}