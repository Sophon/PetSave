package com.example.petsaveedu.common.domain.model.animal

import org.junit.Assert.assertEquals
import org.junit.Test

class PhotoTests {

    private val mediumPhoto = "mediumPhoto"
    private val fullPhoto = "fullPhoto"
    private val invalidPhoto = ""

    @Test
    fun photo_getSmallestPhoto_hasMediumPhoto() {
        //Given
        val photo = Media.Photo(mediumPhoto, fullPhoto)
        val expectedValue = mediumPhoto

        //When
        val smallestPhoto = photo.getSmallestAvailablePhoto()

        //Then
        assertEquals(smallestPhoto, expectedValue)
    }

    @Test
    fun photo_getSmallestPhoto_noMediumPhoto() {
        //Given
        val photo = Media.Photo(invalidPhoto, fullPhoto)
        val expectedValue = fullPhoto

        //When
        val smallestPhoto = photo.getSmallestAvailablePhoto()

        //Then
        assertEquals(smallestPhoto, expectedValue)
    }

    @Test
    fun photo_getSmallestPhoto_noPhoto() {
        //Given
        val photo = Media.Photo(invalidPhoto, invalidPhoto)
        val expectedValue = invalidPhoto

        //When
        val smallestPhoto = photo.getSmallestAvailablePhoto()

        //Then
        assertEquals(smallestPhoto, expectedValue)
    }
}