package com.alavpa.meep.data.api

import com.alavpa.meep.data.api.model.MeepResponse
import io.reactivex.Single

interface RemoteDataSource {
    fun getResources(
        zone: String,
        lowerLeft: Pair<Double, Double>,
        upperRight: Pair<Double, Double>
    ): Single<List<MeepResponse>>
}