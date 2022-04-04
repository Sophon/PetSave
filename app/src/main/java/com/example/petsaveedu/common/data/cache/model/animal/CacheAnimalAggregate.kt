package com.example.petsaveedu.common.data.cache.model.animal

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

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
)
