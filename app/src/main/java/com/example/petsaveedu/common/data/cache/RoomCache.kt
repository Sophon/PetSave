package com.example.petsaveedu.common.data.cache

import com.example.petsaveedu.common.data.cache.daos.AnimalsDao
import com.example.petsaveedu.common.data.cache.daos.OrganizationsDao
import com.example.petsaveedu.common.data.cache.model.animal.CacheAnimalAggregate
import com.example.petsaveedu.common.data.cache.model.org.CacheOrganization
import io.reactivex.Flowable
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val animalsDao: AnimalsDao,
    private val organizationsDao: OrganizationsDao
): Cache {

    override suspend fun storeOrganizations(organizations: List<CacheOrganization>) {
        organizationsDao.insert(organizations)
    }

    override fun getNearbyAnimals(): Flowable<List<CacheAnimalAggregate>> {
        return animalsDao.getAllAnimals()
    }

    override suspend fun storeNearbyAnimals(animals: List<CacheAnimalAggregate>) {
        animalsDao.insertAnimalsWithDetails(animals)
    }
}