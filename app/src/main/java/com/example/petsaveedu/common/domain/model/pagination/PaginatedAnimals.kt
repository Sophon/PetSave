package com.example.petsaveedu.common.domain.model.pagination

import com.example.petsaveedu.common.domain.model.animal.details.AnimalWithDetails

data class PaginatedAnimals(
    val animals: List<AnimalWithDetails>,
    val pagination: Pagination
)