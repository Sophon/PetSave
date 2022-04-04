package com.example.petsaveedu.common.data.cache.model.animal

import androidx.room.Entity
import androidx.room.Index


@Entity(
    primaryKeys = ["animalId", "tag"],
    indices = [Index("tag")]
)
data class CacheAnimalTagCrossRef(
    val animalId: Long,
    val tag: String,
)
