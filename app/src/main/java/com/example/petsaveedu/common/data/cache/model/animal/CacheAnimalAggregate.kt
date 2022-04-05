package com.example.petsaveedu.common.data.cache.model.animal

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.petsaveedu.common.domain.model.animal.details.AnimalWithDetails

data class CacheAnimalAggregate(
    @Embedded val animal: CacheAnimalWithDetails,

    @Relation(parentColumn = "animalId", entityColumn = "animalId")
    val videos: List<CacheVideo>,

    @Relation(parentColumn = "animalId", entityColumn = "animalId")
    val photos: List<CachePhoto>,

    @Relation(
        parentColumn = "animalId",
        entityColumn = "tag",
        associateBy = Junction(CacheAnimalTagCrossRef::class)
    )
    val tags: List<CacheTag>
) {
    companion object {
        fun fromDomain(animalWithDetails: AnimalWithDetails): CacheAnimalAggregate {
            return CacheAnimalAggregate(
                animal = CacheAnimalWithDetails.fromDomain(animalWithDetails),
                photos = animalWithDetails.media.photos.map {
                    CachePhoto.fromDomain(animalWithDetails.id, it)
                },
                videos = animalWithDetails.media.videos.map {
                    CacheVideo.fromDomain(animalWithDetails.id, it)
                },
                tags =  animalWithDetails.tags.map { CacheTag(it) }
            )
        }
    }
}
