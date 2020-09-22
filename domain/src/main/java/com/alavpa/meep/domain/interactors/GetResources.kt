package com.alavpa.meep.domain.interactors

import com.alavpa.meep.domain.model.MeepResource
import com.alavpa.meep.domain.repository.Repository
import io.reactivex.Single

class GetResources(private val repository: Repository) : Interactor<List<MeepResource>> {
    lateinit var zone: String
    lateinit var lowerLeft: Pair<Double, Double>
    lateinit var upperRight: Pair<Double, Double>
    override fun build(): Single<List<MeepResource>> {
        return repository.getResources(zone, lowerLeft, upperRight)
    }
}