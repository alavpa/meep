package com.alavpa.meep.domain.repository

import com.alavpa.meep.domain.model.MeepResource
import io.reactivex.Single

interface Repository {
    fun getResources(
        zone: String,
        lowerLeft: Pair<Double, Double>,
        upperRight: Pair<Double, Double>
    ): Single<List<MeepResource>>
}