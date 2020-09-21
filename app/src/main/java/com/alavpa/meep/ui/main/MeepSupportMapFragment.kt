package com.alavpa.meep.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class MeepSupportMapFragment : SupportMapFragment() {

    fun initMap(googleMap: GoogleMap){
        val lisbon = LatLngBounds.builder()
            .include(LatLng(38.711046, -9.160096))
            .include(LatLng(38.739429, -9.137115))
            .build()

        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngBounds(
                lisbon,
                10
            )
        )
    }
}