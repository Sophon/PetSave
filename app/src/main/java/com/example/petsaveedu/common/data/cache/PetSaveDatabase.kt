package com.example.petsaveedu.common.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.petsaveedu.common.data.cache.daos.AnimalsDao
import com.example.petsaveedu.common.data.cache.daos.OrganizationsDao
import com.example.petsaveedu.common.data.cache.model.animal.*
import com.example.petsaveedu.common.data.cache.model.org.CacheOrganization


@Database(
    entities = [
        CacheAnimalTagCrossRef::class,
        CacheAnimalWithDetails::class,
        CachePhoto::class,
        CacheTag::class,
        CacheVideo::class,
        CacheOrganization::class
    ],
    version = 1
)
abstract class PetSaveDatabase: RoomDatabase() {
    abstract fun organizationsDao(): OrganizationsDao
    abstract fun animalsDao(): AnimalsDao
}