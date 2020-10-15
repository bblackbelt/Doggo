package com.doggo.adapter

import com.doggo.adapter.model.Breed
import com.doggo.domain.model.BreedModelDto

interface BreedsAdapter {

    fun getBreed(breedModelDto: BreedModelDto): Breed
    fun getBreeds(breedModelDto: List<BreedModelDto>): List<Breed>

    class Impl : BreedsAdapter {
        override fun getBreed(breedModelDto: BreedModelDto): Breed = with(breedModelDto) {
           Breed(id, name, lifeSpan, temperament ?: "c")
        }
        override fun getBreeds(breedModelDto: List<BreedModelDto>): List<Breed> = breedModelDto.map { getBreed(it) }
    }
}