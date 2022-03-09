package com.example.petsaveedu.common.data.api.model.mappers

import com.example.petsaveedu.common.data.api.model.ApiEnvironment
import com.example.petsaveedu.common.domain.model.animal.details.HabitatAdaptation
import javax.inject.Inject

class ApiHabitatAdapter @Inject constructor(): ApiMapper<ApiEnvironment?, HabitatAdaptation> {

    override fun mapToDomain(apiEntity: ApiEnvironment?): HabitatAdaptation {
        return HabitatAdaptation(
            goodWithDogs = apiEntity?.dogs,
            goodWithChildren = apiEntity?.children,
            goodWithCats = apiEntity?.cats
        )
    }
}