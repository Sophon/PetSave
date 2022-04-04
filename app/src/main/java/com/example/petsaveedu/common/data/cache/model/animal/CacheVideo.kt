package com.example.petsaveedu.common.data.cache.model.animal

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "videos",
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
data class CacheVideo(
    @PrimaryKey(autoGenerate = true) val videoId: Long = 0,
    val animalId: Long,

    val video: String
)
