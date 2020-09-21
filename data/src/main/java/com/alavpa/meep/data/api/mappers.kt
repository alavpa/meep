package com.alavpa.meep.data.api

import com.alavpa.meep.data.api.model.MeepResponse
import com.alavpa.meep.domain.model.MeepResource
import com.alavpa.meep.domain.model.MeepResource1
import com.alavpa.meep.domain.model.MeepResource2
import com.alavpa.meep.domain.model.MeepResource3
import com.alavpa.meep.domain.model.MeepResource4

fun MeepResponse.toResource(): MeepResource {
    return when (this.companyZoneId) {
        MeepResource.COMPANY_467 -> this.toResource1()
        MeepResource.COMPANY_473 -> this.toResource2()
        MeepResource.COMPANY_412 -> this.toResource3()
        else -> this.toResource4()
    }
}

fun MeepResponse.toResource4(): MeepResource4 {
    return MeepResource4(
        this.id ?: "",
        this.name ?: "",
        this.x ?: 0.0,
        this.y ?: 0.0,
        this.scheduledArrival ?: 0,
        this.locationType ?: 0,
        this.companyZoneId ?: 0,
        this.lat ?: 0.0,
        this.lon ?: 0.0
    )
}

private fun MeepResponse.toResource1(): MeepResource1 {
    return MeepResource1(
        this.id ?: "",
        this.name ?: "",
        this.x ?: 0.0,
        this.y ?: 0.0,
        this.licencePlate ?: "",
        this.range ?: 0,
        this.batteryLevel ?: 0,
        this.seats ?: 0,
        this.model ?: "",
        this.resourceImageId ?: "",
        this.realTimeData ?: false,
        this.resourceType ?: "",
        this.companyZoneId ?: 0
    )
}

private fun MeepResponse.toResource2(): MeepResource2 {
    return MeepResource2(
        this.id ?: "",
        this.name ?: "",
        this.x ?: 0.0,
        this.y ?: 0.0,
        this.licencePlate ?: "",
        this.range ?: 0,
        this.batteryLevel ?: 0,
        this.helmets ?: 0,
        this.model ?: "",
        this.resourceImageId ?: "",
        this.realTimeData ?: false,
        this.resourceType ?: "",
        this.companyZoneId ?: 0
    )
}

private fun MeepResponse.toResource3(): MeepResource3 {
    return MeepResource3(
        this.id ?: "",
        this.name ?: "",
        this.x ?: 0.0,
        this.y ?: 0.0,
        this.realTimeData ?: false,
        this.station ?: false,
        this.availableResources ?: 0,
        this.spacesAvailable ?: 0,
        this.allowDropoff ?: false,
        this.bikesAvailable ?: 0,
        this.companyZoneId ?: 0
    )
}
