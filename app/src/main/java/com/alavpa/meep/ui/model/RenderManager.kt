package com.alavpa.meep.ui.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alavpa.meep.domain.model.MeepResource
import com.google.android.gms.maps.model.MarkerOptions

class RenderManager {

    private val renders: List<RenderResource> = listOf(
        RenderResource378(),
        RenderResource382(),
        RenderResource402(),
        RenderResource412(),
        RenderResource467(),
        RenderResource473()
    )

    fun getView(resource: MeepResource?, inflater: LayoutInflater, viewGroup: ViewGroup?): View? {
        return renders.find { resource?.companyZoneId == it.getCompanyZoneId() }
            ?.getView(inflater, viewGroup)
    }

    fun getMarkerOptions(resource: MeepResource?): MarkerOptions? {
        return resource?.let {
            renders.find { resource.companyZoneId == it.getCompanyZoneId() }
                ?.getMarkerOptions(resource)
        }
    }

    fun populateInfo(view: View, resource: MeepResource) {
        renders.find { resource.companyZoneId == it.getCompanyZoneId() }
            ?.populateInfo(view, resource)
    }
}