package com.alavpa.meep.ui.utils

import com.alavpa.meep.domain.model.MeepResource
import com.alavpa.meep.ui.main.toMarker
import com.google.android.gms.maps.GoogleMap

class MarkerManager {
    fun addMarker(googleMap: GoogleMap?, resource: MeepResource) {
        googleMap?.addMarker(resource.toMarker())
    }
}