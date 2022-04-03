package com.example.petsaveedu.common.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface PetFinderApi {

    @GET(ApiConstants.ANIMALS_ENDPOINT)
    suspend fun getNearbyAnimals(
        @Query("page") pageToLoad: Int,
        @Query("limit") pageSize: Int,
        @Query("distance") maxDistance: Int,
        @Query("location") postCode: String
    )

}