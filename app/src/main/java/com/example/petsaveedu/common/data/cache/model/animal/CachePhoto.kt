package com.example.petsaveedu.common.data.cache.model.animal

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "photos",
    foreignKeys = [
        ForeignKey(
            entity = CacheAnimalWithDetails::class,
            parentColumns = ["animalId"],
            childColumns = ["animalId"],
            onDelete = CASCADE
        )
    ],
    indices = [ Index("animalId") ]
)
data class CachePhoto(
    @PrimaryKey(autoGenerate = true) val photoId: Long,
    val animalId: Long,

    val medium: String,
    val full: String
)
