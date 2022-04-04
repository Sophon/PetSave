package com.example.petsaveedu.common.data.cache.daos

import androidx.room.*
import com.example.petsaveedu.common.data.cache.model.animal.*
import io.reactivex.Flowable

@Dao
interface AnimalsDao {

    @Transaction
    @Query("SELECT * FROM animals ORDER BY animalId DESC")
    abstract fun getAllAnimals(): Flowable<List<CacheAnimalAggregate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAnimalAggregate(
        animal: CacheAnimalWithDetails,
        photos: List<CachePhoto>,
        videos: List<CacheVideo>,
        tags: List<CacheTag>
    )

    suspend fun insertAnimalsWithDetails(animalAggregates: List<CacheAnimalAggregate>) {
        for(animal in animalAggregates) {
            insertAnimalAggregate(
                animal.animal,
                animal.photos,
                animal.videos,
                animal.tags
            )
        }
    }
}