package domain.model

import com.doggo.domain.model.BreedDto

data class DoggoDto(
    val breeds: List<BreedDto>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)