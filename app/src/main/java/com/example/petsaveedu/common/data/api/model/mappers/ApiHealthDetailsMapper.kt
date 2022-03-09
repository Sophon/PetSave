package com.example.petsaveedu.common.data.api.model.mappers

import com.example.petsaveedu.common.data.api.model.ApiAttributes
import com.example.petsaveedu.common.domain.model.animal.details.HealthDetails
import javax.inject.Inject

class ApiHealthDetailsMapper @Inject constructor(): ApiMapper<ApiAttributes?, HealthDetails> {

    override fun mapToDomain(apiEntity: ApiAttributes?): HealthDetails {
        return HealthDetails(
            isDeclawed = apiEntity?.declawed,
            hasSpecialNeeds = apiEntity?.specialNeeds,
            isSpayedOrNeutered = apiEntity?.spayedNeutered,
            shotsAreCurrent = apiEntity?.shotsCurrent
        )
    }
}