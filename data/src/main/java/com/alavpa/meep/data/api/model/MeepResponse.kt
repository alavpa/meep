package com.alavpa.meep.data.api.model

data class MeepResponse(
    val id: String? = null,
    val name: String? = null,
    val x: Double? = null,
    val y: Double? = null,
    val scheduledArrival: Int? = null,
    val locationType: Int? = null,
    val companyZoneId: Long? = null,
    val licencePlate: String? = null,
    val range: Int? = null,
    val batteryLevel: Int? = null,
    val seats: Int? = null,
    val model: String? = null,
    val resourceImageId: String? = null,
    val realTimeData: Boolean? = null,
    val resourceType: String? = null,
    val helmets: Int? = null,
    val station: Boolean? = null,
    val availableResources: Int? = null,
    val spacesAvailable: Int? = null,
    val allowDropoff: Boolean? = null,
    val bikesAvailable: Int? = null,
    val lat: Double? = null,
    val lon: Double? = null
)