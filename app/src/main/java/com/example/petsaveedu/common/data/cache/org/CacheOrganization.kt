package com.example.petsaveedu.common.data.cache.org

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "organizations")
data class CacheOrganization(
    @PrimaryKey(autoGenerate = false) val organizationId: String,

    val email: String,
    val phone: String,

    val address1: String,
    val address2: String,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String,

    val distance: Float
)
