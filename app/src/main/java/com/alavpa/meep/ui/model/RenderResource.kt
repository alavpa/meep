package com.alavpa.meep.ui.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alavpa.meep.domain.model.MeepResource
import com.google.android.gms.maps.model.MarkerOptions

interface RenderResource {

    fun getCompanyZoneId(): Long

    fun getMarkerOptions(resource: MeepResource): MarkerOptions

    fun getView(inflater: LayoutInflater, container: ViewGroup?): View?

    fun populateInfo(view: View, resource: MeepResource?)
}