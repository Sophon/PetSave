package com.example.petsaveedu.common.data.api.model.mappers

import com.example.petsaveedu.common.data.api.model.ApiContact
import com.example.petsaveedu.common.domain.model.organization.Organization.*
import javax.inject.Inject

class ApiContactMapper @Inject constructor(
    private val apiAddressMapper: ApiAddressMapper
): ApiMapper<ApiContact?, Contact> {

    override fun mapToDomain(apiEntity: ApiContact?): Contact {
        return Contact(
            email = apiEntity?.email.orEmpty(),
            phone = apiEntity?.phone.orEmpty(),
            address = apiAddressMapper.mapToDomain(apiEntity?.address)
        )
    }
}