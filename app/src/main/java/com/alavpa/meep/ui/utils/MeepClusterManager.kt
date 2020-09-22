package com.alavpa.meep.ui.utils

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager

class MeepClusterManager(context: Context, googleMap: GoogleMap) :
    ClusterManager<MeepClusterManager.MeepClusterItem>(context, googleMap) {

    class Provider {
        fun provide(context: Context, googleMap: GoogleMap?): MeepClusterManager? {
            return googleMap?.let {
                MeepClusterManager(context, it).apply {
                    renderer = MeepClusterRenderer(context, it, this)
                }
            }
        }
    }

    class MeepClusterItem(private val position: LatLng) : ClusterItem {
        override fun getPosition(): LatLng {
            return position
        }

        override fun getTitle(): String {
            return "hola"
        }

        override fun getSnippet(): String {
            return "mundo"
        }


    }
}