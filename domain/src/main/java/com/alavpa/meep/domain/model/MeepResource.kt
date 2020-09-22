package com.alavpa.meep.domain.model

data class MeepResource(
    val id: String = "",
    val name: String = "",
    val x: Double = 0.0,
    val y: Double = 0.0,
    val scheduledArrival: Int = 0,
    val locationType: Int = 0,
    val companyZoneId: Long = 0,
    val licencePlate: String = "",
    val range: Int = 0,
    val batteryLevel: Int = 0,
    val seats: Int = 0,
    val model: String = "",
    val resourceImageId: String = "",
    val realTimeData: Boolean = false,
    val resourceType: String = "",
    val helmets: Int = 0,
    val station: Boolean = false,
    val availableResources: Int = 0,
    val spacesAvailable: Int = 0,
    val allowDropoff: Boolean = false,
    val bikesAvailable: Int = 0,
    val lat: Double = 0.0,
    val lon: Double = 0.0
){
    companion object{
        const val COMPANY_378 = 378L
        const val COMPANY_382 = 382L
        const val COMPANY_402 = 402L
        const val COMPANY_412 = 412L
        const val COMPANY_467 = 467L
        const val COMPANY_473 = 473L
    }
}