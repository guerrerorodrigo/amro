package com.rodrigoguerrero.domain.home.impl.mappers

import com.rodrigoguerrero.domain.common.DateConverter
import com.rodrigoguerrero.domain.common.ImageSize
import com.rodrigoguerrero.domain.common.ImageUrlBuilder
import com.rodrigoguerrero.domain.home.impl.testdata.expectedTrending
import com.rodrigoguerrero.domain.home.impl.testdata.genres
import com.rodrigoguerrero.domain.home.impl.testdata.trending
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class TrendingMovieMapperTest {
    private val genresMapper = mockk<GenresMapper> {
        every { toGenres(any()) } returns genres
        every { filterGenresByIds(any(), any()) } returns genres
    }
    private val imageUrlBuilder = mockk<ImageUrlBuilder> {
        every { buildUrl(any(), any()) } returns "image url"
    }
    private val dateConverter = mockk<DateConverter> {
        every { toEpochMilliseconds(any()) } returns 1234500000L
    }
    private val subject = TrendingMovieMapper(
        genresMapper = genresMapper,
        imageUrlBuilder = imageUrlBuilder,
        dateConverter = dateConverter,
    )

    @Test
    fun toTrendingMoviesTest() {
        val result = subject.toTrendingMovies(
            trendingMovies = listOf(trending),
            genres = genres,
        )

        assertAll(
            { assertEquals(expectedTrending, result.first()) },
            { verify(exactly = 1) { genresMapper.filterGenresByIds(trending.genreIds, genres) } },
            {
                verify(exactly = 1) {
                    genresMapper.filterGenresByIds(genreIds = listOf(1, 2), genres = genres)
                }
            },
            { verify(exactly = 1) { imageUrlBuilder.buildUrl("image url path", ImageSize.W300) } },
        )
    }
}
