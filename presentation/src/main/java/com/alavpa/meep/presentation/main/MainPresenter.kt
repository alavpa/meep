package com.alavpa.meep.presentation.main

import androidx.lifecycle.MutableLiveData
import com.alavpa.meep.domain.interactors.GetResources
import com.alavpa.meep.domain.model.MeepResource
import com.alavpa.meep.presentation.base.Presenter

class MainPresenter(private val getResources: GetResources) : Presenter() {

    companion object {
        private const val ZONE = "lisboa"
    }

    val populateLiveData = MutableLiveData<List<MeepResource>?>()
    val selectResourceLiveData = MutableLiveData<MeepResource?>()

    fun getResources(
        lowerLeftLat: Double,
        lowerLeftLon: Double,
        upperRightLat: Double,
        upperRightLon: Double
    ) {
        getResources.zone = ZONE
        getResources.lowerLeft = Pair(lowerLeftLat, lowerLeftLon)
        getResources.upperRight = Pair(upperRightLat, upperRightLon)
        getResources.build().exec {
            populateLiveData.value = it
        }
    }

    fun selectResource(meepResource: MeepResource?) {
        selectResourceLiveData.value = meepResource
    }
}