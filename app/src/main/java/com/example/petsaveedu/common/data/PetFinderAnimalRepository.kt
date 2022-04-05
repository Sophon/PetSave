package com.example.petsaveedu.common.data

import com.example.petsaveedu.common.data.api.PetFinderApi
import com.example.petsaveedu.common.data.api.model.mappers.ApiAnimalMapper
import com.example.petsaveedu.common.data.api.model.mappers.ApiPaginationMapper
import com.example.petsaveedu.common.data.cache.Cache
import com.example.petsaveedu.common.data.cache.model.animal.CacheAnimalAggregate
import com.example.petsaveedu.common.data.cache.model.org.CacheOrganization
import com.example.petsaveedu.common.domain.model.animal.Animal
import com.example.petsaveedu.common.domain.model.animal.details.AnimalWithDetails
import com.example.petsaveedu.common.domain.model.pagination.PaginatedAnimals
import com.example.petsaveedu.common.domain.repositories.AnimalRepository
import io.reactivex.Flowable
import javax.inject.Inject

class PetFinderAnimalRepository @Inject constructor(
    private val api: PetFinderApi,
    private val cache: Cache,
    private val apiAnimalMapper: ApiAnimalMapper,
    private val apiPaginationMapper: ApiPaginationMapper
): AnimalRepository {

    // fetch these from shared preferences, after storing them in onboarding screen
    private val postcode = "07097"
    private val maxDistanceMiles = 100

    override fun getAnimals(): Flowable<List<Animal>> {
        return cache.getNearbyAnimals()
            .distinctUntilChanged()
            .map { animalAggregateList ->
                animalAggregateList.map {
                    it.animal.toDomainAnimal(it.photos, it.videos, it.tags)
                }
            }
    }

    override suspend fun requestMoreAnimals(pageToLoad: Int, numberOfItems: Int): PaginatedAnimals {
        val (apiAnimals, apiPagination) =
            api.getNearbyAnimals(pageToLoad, numberOfItems, maxDistanceMiles, postcode)

        return PaginatedAnimals(
            apiAnimals?.map {
                apiAnimalMapper.mapToDomain(it)
            }.orEmpty(),
            apiPaginationMapper.mapToDomain(apiPagination)
        )
    }

    override suspend fun storeAnimals(animals: List<AnimalWithDetails>) {
        cache.storeOrganizations(
            animals.map {
               CacheOrganization.fromDomain(it.details.organization)
            }
        )

        cache.storeNearbyAnimals(
            animals.map {
                CacheAnimalAggregate.fromDomain(it)
            }
        )
    }
}