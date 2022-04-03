package com.example.petsaveedu.common.data.api.model.mappers

import com.example.petsaveedu.common.data.api.model.ApiAddress
import com.example.petsaveedu.common.domain.model.organization.Organization.*
import javax.inject.Inject

class ApiAddressMapper @Inject constructor(): ApiMapper<ApiAddress?, Address> {

    override fun mapToDomain(apiEntity: ApiAddress?): Address {
        return Address(
            address1 = apiEntity?.address1.orEmpty(),
            address2 = apiEntity?.address2.orEmpty(),
            city = apiEntity?.city.orEmpty(),
            state = apiEntity?.state.orEmpty(),
            postcode = apiEntity?.postcode.orEmpty(),
            country = apiEntity?.country.orEmpty()
        )
    }
}