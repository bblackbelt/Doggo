package com.doggo.controllers

import com.doggo.adapter.DoggoAdapter
import com.doggo.adapter.model.Doggo
import com.doggo.domain.DoggoService


interface DoggoController {
    class Impl constructor(private val doggoService: DoggoService, private val doggoAdapter: DoggoAdapter): DoggoController {
        override suspend fun getDoggos(page: Int, limit: Int, breedId: String?)
                = doggoService.getDoggos(page, limit, breedId).map { doggoAdapter.getDoggo(it) }
    }
    suspend fun getDoggos(page: Int, limit: Int = 25, breedId: String? = null): List<Doggo>
}