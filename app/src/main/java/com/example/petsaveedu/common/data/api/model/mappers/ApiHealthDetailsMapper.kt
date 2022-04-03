package com.example.petsaveedu.common.data.api.model.mappers

import com.example.petsaveedu.common.data.api.model.ApiAttributes
import com.example.petsaveedu.common.domain.model.animal.details.HealthDetails
import javax.inject.Inject

class ApiHealthDetailsMapper @Inject constructor(): ApiMapper<ApiAttributes?, HealthDetails> {

    override fun mapToDomain(apiEntity: ApiAttributes?): HealthDetails {
        return HealthDetails(
            isDeclawed = apiEntity?.declawed ?: false,
            hasSpecialNeeds = apiEntity?.specialNeeds ?: false,
            isSpayedOrNeutered = apiEntity?.spayedNeutered ?: false,
            shotsAreCurrent = apiEntity?.shotsCurrent ?: false
        )
    }
}