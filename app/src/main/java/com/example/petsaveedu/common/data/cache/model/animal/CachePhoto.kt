package com.example.petsaveedu.common.data.cache.model.animal

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.petsaveedu.common.domain.model.animal.Media

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
    @PrimaryKey(autoGenerate = true) val photoId: Long = 0,
    val animalId: Long,

    val medium: String,
    val full: String
) {
    companion object {
        fun fromDomain(animalId: Long, photo: Media.Photo): CachePhoto {
            return CachePhoto(
                animalId = animalId,
                medium = photo.medium,
                full = photo.full
            )
        }
    }

    fun toDomain(): Media.Photo {
        return Media.Photo(medium, full)
    }
}
