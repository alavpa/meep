package com.alavpa.meep.domain.model

class MeepResource2(
    id: String = "",
    name: String = "",
    x: Double = 0.0,
    y: Double = 0.0,
    val licencePlate: String = "",
    val range: Int = 0,
    val batteryLevel: Int = 0,
    val helmets: Int = 0,
    val model: String = "",
    val resourceImageId: String = "",
    val realTimeData: Boolean = false,
    val resourceType: String,
    companyZoneId: Int = 0
) : MeepResource(id, name, x, y, companyZoneId)