package com.example.petsaveedu.common.data.di

import android.content.Context
import androidx.room.Room
import com.example.petsaveedu.common.data.cache.Cache
import com.example.petsaveedu.common.data.cache.PetSaveDatabase
import com.example.petsaveedu.common.data.cache.RoomCache
import com.example.petsaveedu.common.data.cache.daos.AnimalsDao
import com.example.petsaveedu.common.data.cache.daos.OrganizationsDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): PetSaveDatabase {
            return Room.databaseBuilder(
                context,
                PetSaveDatabase::class.java,
                "petsaveedu.db"
            ).build()
        }

        @Provides
        fun provideAnimalsDao(petSaveDatabase: PetSaveDatabase): AnimalsDao {
            return petSaveDatabase.animalsDao()
        }

        @Provides
        fun providesOrganizationsDao(petSaveDatabase: PetSaveDatabase): OrganizationsDao {
            return petSaveDatabase.organizationsDao()
        }
    }
}