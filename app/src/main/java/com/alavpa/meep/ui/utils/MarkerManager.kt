package com.alavpa.meep.ui.utils

import com.alavpa.meep.domain.model.MeepResource
import com.alavpa.meep.ui.model.RenderManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

class MarkerManager(
    private val renderManager: RenderManager,
    private val clusterManager: MeepClusterManager
) {

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

    fun addClusters(resources: List<MeepResource>){
        clusterManager.clearItems()
        resources.forEach {
            clusterManager.addItem(MeepClusterManager.MeepClusterItem(LatLng(it.y, it.x)))
        }
        clusterManager.cluster()
    }
}