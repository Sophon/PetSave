package com.example.petsaveedu.common.domain.repositories

import com.example.petsaveedu.common.domain.model.animal.Animal
import com.example.petsaveedu.common.domain.model.animal.details.AnimalWithDetails
import com.example.petsaveedu.common.domain.model.pagination.PaginatedAnimals
import io.reactivex.Flowable

interface AnimalRepository {

    fun getAnimals(): Flowable<List<Animal>>

    suspend fun requestMoreAnimals(pageToLoad: Int, numberOfItems: Int): PaginatedAnimals

    suspend fun storeAnimals(animals: List<AnimalWithDetails>)
}