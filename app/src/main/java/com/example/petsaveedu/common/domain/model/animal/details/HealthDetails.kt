package com.example.petsaveedu.common.domain.model.animal.details

data class HealthDetails(
    val isDeclawed: Boolean,
    val hasSpecialNeeds: Boolean,
    val isSpayedOrNeutered: Boolean,
    val shotsAreCurrent: Boolean
)
