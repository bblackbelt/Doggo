package com.doggo.controllers

import com.doggo.adapter.BreedsAdapter
import com.doggo.adapter.model.Breed
import com.doggo.domain.BreedsService
import com.doggo.domain.model.BreedModelDto


interface BreedsController {
    class Impl constructor(private val breedsService: BreedsService, private val breedsAdapter: BreedsAdapter): BreedsController {
        override suspend fun getBreeds() = breedsService.getBreeds().map { breedsAdapter.getBreed(it) }
    }
    suspend fun getBreeds(): List<Breed>
}