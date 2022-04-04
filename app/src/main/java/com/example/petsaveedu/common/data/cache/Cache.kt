package com.example.petsaveedu.common.data.cache

import com.example.petsaveedu.common.data.cache.model.animal.CacheAnimalAggregate
import com.example.petsaveedu.common.data.cache.model.org.CacheOrganization
import io.reactivex.Flowable

interface Cache {

    suspend fun storeOrganizations(organizations: List<CacheOrganization>)

    fun getNearbyAnimals(): Flowable<List<CacheAnimalAggregate>>

    suspend fun storeNearbyAnimals(animals: List<CacheAnimalAggregate>)
}