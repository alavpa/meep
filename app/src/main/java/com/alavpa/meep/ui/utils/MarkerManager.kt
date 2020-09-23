package com.alavpa.meep.ui.utils

import com.alavpa.meep.domain.model.MeepResource
import com.alavpa.meep.ui.model.RenderManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

class MarkerManager(private val renderManager: RenderManager) {

    fun addMarker(googleMap: GoogleMap?, resource: MeepResource) {
        renderManager.getMarkerOptions(resource)?.also { markerOptions ->
            googleMap?.addMarker(markerOptions)?.also { it.tag = resource }
        }
    }

    fun addMarkers(googleMap: GoogleMap?, resources: List<MeepResource>) {
        resources.forEach {
            addMarker(googleMap, it)
        }
    }
}