package com.alavpa.meep.data

import com.alavpa.meep.data.api.RemoteDataSource
import com.alavpa.meep.data.api.toResource
import com.alavpa.meep.data.exceptions.ApiServerException
import com.alavpa.meep.domain.exceptions.NoInternetException
import com.alavpa.meep.domain.exceptions.ServerException
import com.alavpa.meep.domain.model.MeepResource
import com.alavpa.meep.domain.repository.Repository
import io.reactivex.Single

class DataRepository(private val apiDataSource: RemoteDataSource) : Repository {

    override fun getResources(
        zone: String,
        lowerLeft: Pair<Double, Double>,
        upperRight: Pair<Double, Double>
    ): Single<List<MeepResource>> {
        return apiDataSource.getResources(zone, lowerLeft, upperRight).onErrorResumeNext {
            when (it) {
                is ApiServerException -> Single.error(ServerException())
                else -> Single.error(NoInternetException())
            }
        }.map { list -> list.map { it.toResource() } }
    }
}