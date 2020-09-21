package com.alavpa.meep.ui.utils

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class MeepClusterRenderer(
    context: Context,
    googleMap: GoogleMap,
    clusterManager: MeepClusterManager
) : DefaultClusterRenderer<MeepClusterManager.MeepClusterItem>(context, googleMap, clusterManager) {

    override fun shouldRenderAsCluster(cluster: Cluster<MeepClusterManager.MeepClusterItem>?): Boolean {
        return true
    }
}