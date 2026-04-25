package com.rodrigoguerrero.domain.details.impl.mapper

import com.rodrigoguerrero.domain.common.CurrencyFormatter
import com.rodrigoguerrero.domain.common.DateConverter
import com.rodrigoguerrero.domain.common.ImageSize
import com.rodrigoguerrero.domain.common.ImageUrlBuilder
import com.rodrigoguerrero.domain.details.impl.expectedDetails
import com.rodrigoguerrero.domain.details.impl.mappers.MovieDetailsMapper
import com.rodrigoguerrero.domain.details.impl.repoDetails
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class MovieDetailsMapperTest {
    private val imageUrlBuilder = mockk<ImageUrlBuilder>()
    private val currencyFormatter = mockk<CurrencyFormatter>()
    private val dateConverter = mockk<DateConverter>()
    private val subject = MovieDetailsMapper(
        imageUrlBuilder = imageUrlBuilder,
        currencyFormatter = currencyFormatter,
        dateConverter = dateConverter,
    )

    @Test
    @DisplayName(
        """
            Given a repo MovieDetails model with valid data
            When calling toMovieDetails
            Then expected movie details has correct values, and correct methods are called with
            correct data
        """
    )
    fun toMovieDetailsTest() {
        every { imageUrlBuilder.buildUrl(any(), any()) } returns "url/posterPath"
        every { currencyFormatter.format(100000) } returns "$100,000"
        every { currencyFormatter.format(2000000) } returns "$2,000,000"
        every { dateConverter.toReadableDate(any()) } returns "formated releaseDate"

        val result = subject.toMovieDetails(repoDetails)
        assertAll(
            { assertEquals(expectedDetails, result) },
            { verify(exactly = 1) { imageUrlBuilder.buildUrl("/posterPath", ImageSize.Original) } },
            { verify(exactly = 1) { currencyFormatter.format(100000) } },
            { verify(exactly = 1) { currencyFormatter.format(2000000) } },
            { verify(exactly = 1) { dateConverter.toReadableDate("releaseDate") } },
        )
    }
}
