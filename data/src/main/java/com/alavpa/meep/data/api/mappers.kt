package com.alavpa.meep.data.api

import com.alavpa.meep.data.api.model.MeepResponse
import com.alavpa.meep.domain.model.MeepResource

fun MeepResponse.toResource(): MeepResource {
    return MeepResource(
        this.id ?: "",
        this.name ?: "",
        this.x ?: 0.0,
        this.y ?: 0.0,
        this.scheduledArrival ?: 0,
        this.locationType ?: 0,
        this.companyZoneId ?: 0,
        this.licencePlate ?: "",
        this.range ?: 0,
        this.batteryLevel ?: 0,
        this.seats ?: 0,
        this.model ?: "",
        this.resourceImageId ?: "",
        this.realTimeData ?: false,
        this.resourceType ?: "",
        this.helmets ?: 0,
        this.station ?: false,
        this.availableResources ?: 0,
        this.spacesAvailable ?: 0,
        this.allowDropoff ?: false,
        this.bikesAvailable ?: 0,
        this.lat ?: 0.0,
        this.lon ?: 0.0
    )
}
