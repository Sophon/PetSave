package com.example.petsaveedu.common.data.api.model.mappers

import javax.inject.Inject

class ApiAnimalMapper @Inject constructor(
    private val apiBreedsMapper: ApiBreedsMapper,
    private val apiColorsMapper: ApiColorsMapper,
    private val apiContactMapper: ApiContactMapper,
    private val apiHabitatAdapter: ApiHabitatAdapter,
    private val apiPhotoMapper: ApiPhotoMapper,
    private val apiVideoMapper: ApiVideoMapper,
    private val apiHealthDetailsMapper: ApiHealthDetailsMapper
) {

    //TODO
}