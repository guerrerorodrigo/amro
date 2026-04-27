package com.rodrigoguerrero.ui.feature.details.mappers

import com.rodrigoguerrero.ui.feature.details.testdata.domainMovieDetails
import com.rodrigoguerrero.ui.feature.details.testdata.expectedMovieDetails
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DetailsMapperTest {
    private val subject = DetailsMapper()

    @Test
    @DisplayName(
        """
            Given a domain movie details object with data
            When mapping toDetails
            Then UI movie details has correct data
        """
    )
    fun toDetailsTest() {
        val result = subject.toDetails(domainMovieDetails)
        assertEquals(expectedMovieDetails, result)
    }
}
