package com.alavpa.meep.ui.main

import com.alavpa.meep.domain.model.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

fun MeepResource.toMarker(): MarkerOptions {
    return when (this) {
        is MeepResource1 -> this.toMarker()
        is MeepResource2 -> this.toMarker()
        is MeepResource3 -> this.toMarker()
        is MeepResource4 -> this.toMarker()
        else -> MarkerOptions()
            .position(LatLng(this.y, this.x))
            .title(this.name)
            .snippet(this.id)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
    }
}

private fun MeepResource1.toMarker(): MarkerOptions {
    return MarkerOptions()
        .position(LatLng(this.y, this.x))
        .title(this.name)
        .snippet(this.id)
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
}

private fun MeepResource2.toMarker(): MarkerOptions {
    return MarkerOptions()
        .position(LatLng(this.y, this.x))
        .title(this.name)
        .snippet(this.id)
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
}

private fun MeepResource3.toMarker(): MarkerOptions {
    return MarkerOptions()
        .position(LatLng(this.y, this.x))
        .title(this.name)
        .snippet(this.id)
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
}

private fun MeepResource4.toMarker(): MarkerOptions {
    return MarkerOptions()
        .position(LatLng(this.y, this.x))
        .title(this.name)
        .snippet(this.id)
        .icon(
            when (this.companyZoneId) {
                MeepResource.COMPANY_402 -> BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_YELLOW
                )
                MeepResource.COMPANY_378 -> BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_MAGENTA
                )
                else -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
            }
        )
}