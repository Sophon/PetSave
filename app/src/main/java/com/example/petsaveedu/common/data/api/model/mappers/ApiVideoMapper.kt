package com.example.petsaveedu.common.data.api.model.mappers

import com.example.petsaveedu.common.data.api.model.ApiVideoLink
import com.example.petsaveedu.common.domain.model.animal.Media
import javax.inject.Inject

class ApiVideoMapper @Inject constructor(): ApiMapper<ApiVideoLink?, Media.Video> {

    override fun mapToDomain(apiEntity: ApiVideoLink?): Media.Video {
        return Media.Video(embed = apiEntity?.embed.orEmpty())
    }
}