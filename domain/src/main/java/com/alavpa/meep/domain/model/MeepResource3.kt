package com.alavpa.meep.domain.model

class MeepResource3(
    id: String = "",
    name: String = "",
    x: Double = 0.0,
    y: Double = 0.0,
    val realTimeData: Boolean = false,
    val station: Boolean = false,
    val availableResources: Int = 0,
    val spacesAvailable: Int = 0,
    val allowDropoff: Boolean = false,
    val bikesAvailable: Int = 0,
    companyZoneId: Int = 0
) : MeepResource(id, name, x, y, companyZoneId)