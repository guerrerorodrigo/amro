package com.rodrigoguerrero.domain.common

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ImageUrlBuilderTest {

    private val subject = ImageUrlBuilder()

    @Test
    @DisplayName(
        """
            Given a path and ImageSize
            When building the URL
            Then the returned URL contains the correct base url, path and size
        """
    )
    fun buildUrlTest() {
        val result = subject.buildUrl("/path", ImageSize.W300)
        assertEquals("https://image.tmdb.org/t/p/w300/path", result)
    }
}
