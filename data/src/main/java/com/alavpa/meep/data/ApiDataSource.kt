package com.alavpa.meep.data

import com.alavpa.meep.data.api.Api
import com.alavpa.meep.data.api.RemoteDataSource
import com.alavpa.meep.data.api.model.MeepResponse
import com.alavpa.meep.data.exceptions.ApiServerException
import io.reactivex.Single

class ApiDataSource(private val api: Api) : RemoteDataSource {

    override fun getResources(
        zone: String,
        lowerLeft: Pair<Double, Double>,
        upperRight: Pair<Double, Double>
    ): Single<List<MeepResponse>> {
        return api.getItems(
            zone,
            "${lowerLeft.first},${lowerLeft.second}",
            "${upperRight.first},${upperRight.second}"
        ).map {
            if (it.isSuccessful) it.body() ?: listOf()
            else throw ApiServerException()
        }
    }
}