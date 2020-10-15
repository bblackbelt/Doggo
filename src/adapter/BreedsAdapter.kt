package com.doggo.adapter

import com.doggo.adapter.model.Breed
import com.doggo.domain.model.BreedDto

interface BreedsAdapter {

    fun getBreed(breedDto: BreedDto): Breed
    fun getBreeds(breedDto: List<BreedDto>): List<Breed>

    class Impl : BreedsAdapter {
        override fun getBreed(breedDto: BreedDto): Breed = with(breedDto) {
           Breed(id, name, lifeSpan, temperament ?: "c")
        }
        override fun getBreeds(breedDto: List<BreedDto>): List<Breed> = breedDto.map { getBreed(it) }
    }
}