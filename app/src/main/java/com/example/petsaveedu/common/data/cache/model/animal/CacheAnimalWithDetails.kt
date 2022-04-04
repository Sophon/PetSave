package com.example.petsaveedu.common.data.cache.model.animal

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.petsaveedu.common.data.cache.model.org.CacheOrganization
import com.example.petsaveedu.common.domain.model.animal.AdoptionStatus
import com.example.petsaveedu.common.domain.model.animal.Media
import com.example.petsaveedu.common.domain.model.animal.details.*
import com.example.petsaveedu.common.utils.DateTimeUtils


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
) {
    companion object {
        fun fromDomain(animal: AnimalWithDetails): CacheAnimalWithDetails {
            return CacheAnimalWithDetails(
                animalId = animal.id,
                organizationId = animal.details.organization.id,
                name = animal.name,
                type = animal.type,
                description = animal.details.description,
                age = animal.details.age.toString(),
                species = animal.details.species,
                primaryBreed = animal.details.breed.primary,
                secondaryBreed = animal.details.breed.secondary,
                primaryColor = animal.details.colors.primary,
                secondaryColor = animal.details.colors.secondary,
                tertiaryColor = animal.details.colors.tertiary,
                gender = animal.details.gender.toString(),
                size = animal.details.size.toString(),
                coat = animal.details.coat.toString(),
                isDeclawed = animal.details.healthDetails.isDeclawed,
                hasSpecialNeeds = animal.details.healthDetails.hasSpecialNeeds,
                isSpayedOrNeutered = animal.details.healthDetails.isSpayedOrNeutered,
                shotsAreCurrent = animal.details.healthDetails.shotsAreCurrent,
                isGoodWithDogs = animal.details.habitatAdaptation.goodWithDogs,
                isGoodWithChildren = animal.details.habitatAdaptation.goodWithChildren,
                isGoodWithCats = animal.details.habitatAdaptation.goodWithCats,
                adoptionStatus = animal.adoptionStatus.toString(),
                publishedAt = animal.publishedAt.toString()
            )
        }
    }

    fun toDomain(
        organization: CacheOrganization,
        photos: List<CachePhoto>,
        videos: List<CacheVideo>,
        tags: List<CacheTag>
    ): AnimalWithDetails {
        return AnimalWithDetails(
            id = animalId,
            name = name,
            type = type,
            details = mapDetails(organization),
            media = Media(
                photos = photos.map { it.toDomain() },
                videos = videos.map { it.toDomain() }
            ),
            tags = tags.map { it.tag },
            adoptionStatus = AdoptionStatus.valueOf(adoptionStatus),
            publishedAt = DateTimeUtils.parse(publishedAt)
        )
    }

    private fun mapDetails(organization: CacheOrganization): Details {
        return Details(
            description = description,
            age = Age.valueOf(age),
            species = species,
            breed = Breed(primaryBreed, secondaryBreed),
            colors = Colors(primaryColor, secondaryColor, tertiaryColor),
            gender = Gender.valueOf(gender),
            size = Size.valueOf(size),
            coat = Coat.valueOf(coat),
            healthDetails = HealthDetails(
                isDeclawed = isDeclawed,
                hasSpecialNeeds = hasSpecialNeeds,
                isSpayedOrNeutered = isSpayedOrNeutered,
                shotsAreCurrent = shotsAreCurrent
            ),
            habitatAdaptation = HabitatAdaptation(
                goodWithDogs = isGoodWithDogs,
                goodWithChildren = isGoodWithChildren,
                goodWithCats = isGoodWithCats
            ),
            organization = organization.toDomain()
        )
    }
}