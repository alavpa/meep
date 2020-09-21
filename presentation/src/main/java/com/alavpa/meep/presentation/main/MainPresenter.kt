package com.alavpa.meep.presentation.main

import androidx.lifecycle.MutableLiveData
import com.alavpa.meep.domain.interactors.GetResources
import com.alavpa.meep.domain.model.MeepResource
import com.alavpa.meep.presentation.base.Presenter

class MainPresenter(private val getResources: GetResources) : Presenter() {

    companion object {
        private const val ZONE = "lisboa"
    }

    val renderLiveData = MutableLiveData<ViewModel>()
    private val viewModel: ViewModel
        get() = renderLiveData.value ?: ViewModel()

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
            renderLiveData.value = viewModel.copy(resources = it)
        }
    }

    data class ViewModel(
        val resources: List<MeepResource> = listOf()
    )
}