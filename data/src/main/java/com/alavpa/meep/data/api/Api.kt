package com.alavpa.meep.data.api

import com.alavpa.meep.data.api.model.MeepResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    companion object {
        const val BASE_URL = "https://apidev.meep.me/tripplan/api/v1/"
    }

    @GET("routers/{zone}/resources")
    fun getItems(
        @Path("zone") zone: String,
        @Query("lowerLeftLatLon") lowerLeftLatLon: String,
        @Query("upperRightLatLon") upperRightLatLon: String
    ): Single<Response<List<MeepResponse>>>
}