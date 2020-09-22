package com.alavpa.meep.ui.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alavpa.meep.R
import com.alavpa.meep.domain.model.MeepResource
import com.alavpa.meep.domain.model.MeepResource.Companion.COMPANY_473
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class RenderResource473 : RenderResource {
    override fun getCompanyZoneId(): Long {
        return COMPANY_473
    }

    override fun getMarkerOptions(resource: MeepResource): MarkerOptions {
        return MarkerOptions()
            .position(LatLng(resource.y, resource.x))
            .title(resource.name)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
    }

    override fun getView(inflater: LayoutInflater, container: ViewGroup?): View? {
        return inflater.inflate(R.layout.fargment_info2, container, false)
    }

    override fun populateInfo(view: View, resource: MeepResource?) {
        view.findViewById<TextView>(R.id.tv_title).text = resource?.name ?: ""
        view.findViewById<TextView>(R.id.tv_model).text = resource?.model ?: ""
        view.findViewById<TextView>(R.id.tv_plate).text = resource?.licencePlate ?: ""
        view.findViewById<TextView>(R.id.tv_battery).text =
            resource?.let { "${it.batteryLevel}%" } ?: ""
        view.findViewById<TextView>(R.id.tv_helmet).text = resource?.helmets?.toString() ?: ""
    }
}