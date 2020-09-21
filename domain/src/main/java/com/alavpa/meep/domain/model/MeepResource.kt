package com.alavpa.meep.domain.model

abstract class MeepResource(
    val id: String = "",
    val name: String = "",
    val x: Double = 0.0,
    val y: Double = 0.0,
    val companyZoneId: Int = 0
) {
    companion object{
        const val COMPANY_402 = 402
        const val COMPANY_467 = 467
        const val COMPANY_473 = 473
        const val COMPANY_412 = 412
        const val COMPANY_378 = 378
        const val COMPANY_382 = 382
    }
}