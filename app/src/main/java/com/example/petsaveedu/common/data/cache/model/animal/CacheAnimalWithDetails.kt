package com.example.petsaveedu.common.data.cache.model.animal

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.petsaveedu.common.data.cache.model.org.CacheOrganization


@Entity(
    tableName = "animals",
    foreignKeys = [
        ForeignKey(
            entity = CacheOrganization::class,
            parentColumns = ["organizationId"],
            childColumns = ["organizationId"],
            onDelete = CASCADE
        )
    ],
    indices = [ Index("organizationId") ]
)
data class CacheAnimalWithDetails(
    @PrimaryKey(autoGenerate = false) val animalId: Long,
    val organizationId: String,

    val name: String,
    val type: String,

    val description: String,
    val age: String,
    val species: String,
    val primaryBreed: String,
    val secondaryBreed: String,
    val primaryColor: String,
    val secondaryColor: String,
    val tertiaryColor: String,
    val gender: String,
    val size: String,
    val coat: String,
    val isDeclawed: Boolean,
    val hasSpecialNeeds: Boolean,
    val isSpayedOrNeutered: Boolean,
    val shotsAreCurrent: Boolean,
    val isGoodWithDogs: Boolean,
    val isGoodWithChildren: Boolean,
    val isGoodWithCats: Boolean,

    val adoptionStatus: String,
    val publishedAt: String
)