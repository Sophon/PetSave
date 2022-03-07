package com.example.petsaveedu.common.domain.model.animal.details

data class Breed(
    val primary: String,
    val secondary: String
) {
    val mixed: Boolean = primary.isNotEmpty() && secondary.isNotEmpty()

    val unknown: Boolean = primary.isEmpty() && secondary.isEmpty()
}