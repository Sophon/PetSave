package com.example.petsaveedu.common.data.cache.model.animal

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tags")
data class CacheTag(
    @PrimaryKey(autoGenerate = false) val tag: String
)
