package com.example.petsaveedu.common.domain.model.animal

import org.threeten.bp.LocalDateTime

data class Animal(
    val id: String,
    val name: String,
    val type: String,
    val media: Media,
    val tags: List<String>,
    val adoptionStatus: AdoptionStatus,
    val publishedAt: LocalDateTime
)