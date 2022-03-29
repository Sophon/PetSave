package com.example.petsaveedu.common.utils

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

object DateTimeUtils {
    fun parse(date: String): LocalDateTime {
        return try {
            LocalDateTime.parse(date)
        } catch(e: Exception) {
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
            LocalDateTime.parse(date, dateFormatter)
        }
    }
}