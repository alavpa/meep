package com.alavpa.meep.domain

import com.alavpa.meep.domain.interactors.GetResources
import org.koin.dsl.module

val domainModule = module {
    factory { GetResources(get()) }
}