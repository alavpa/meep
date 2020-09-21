package com.alavpa.meep.domain.model

class MeepResource4(
    id: String = "",
    name: String = "",
    x: Double = 0.0,
    y: Double = 0.0,
    companyZoneId: Int = 0,
    val scheduledArrival: Int = 0,
    val locationType: Int = 0,
    val lat: Double = 0.0,
    val lon: Double = 0.0
) : MeepResource(id, name, x, y, companyZoneId)