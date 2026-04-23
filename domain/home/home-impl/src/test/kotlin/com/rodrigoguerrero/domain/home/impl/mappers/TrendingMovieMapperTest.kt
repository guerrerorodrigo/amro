package com.rodrigoguerrero.domain.home.impl.mappers

import com.rodrigoguerrero.domain.common.ImageSize
import com.rodrigoguerrero.domain.common.ImageUrlBuilder
import com.rodrigoguerrero.domain.home.impl.testdata.anotherGenre
import com.rodrigoguerrero.domain.home.impl.testdata.expectedTrending
import com.rodrigoguerrero.domain.home.impl.testdata.genre
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
    private val subject = TrendingMovieMapper(
        genresMapper = genresMapper,
        imageUrlBuilder = imageUrlBuilder,
    )

    @Test
    fun toTrendingMoviesTest() {
        val genresList = listOf(genre, anotherGenre)
        val result = subject.toTrendingMovies(
            trendingMovies = listOf(trending),
            genres = genresList,
        )

        assertAll(
            { assertEquals(expectedTrending, result.first()) },
            { verify(exactly = 1) { genresMapper.toGenres(genresList) } },
            {
                verify(exactly = 1) {
                    genresMapper.filterGenresByIds(genreIds = listOf(1, 2), genres = genres)
                }
            },
            { verify(exactly = 1) { imageUrlBuilder.buildUrl("image url path", ImageSize.W300) } },
        )
    }
}
