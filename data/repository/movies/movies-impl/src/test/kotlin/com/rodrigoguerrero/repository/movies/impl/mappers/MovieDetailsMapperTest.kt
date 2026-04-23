package com.rodrigoguerrero.repository.movies.impl.mappers

import com.rodrigoguerrero.repository.movies.impl.testdata.expectedGenre
import com.rodrigoguerrero.repository.movies.impl.testdata.expectedMovieDetails
import com.rodrigoguerrero.repository.movies.impl.testdata.movieDetailsDto
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MovieDetailsMapperTest {
    private val genresMapper = mockk<GenreMapper> {
        every { toGenres(any()) } returns listOf(expectedGenre)
    }
    private val subject = MovieDetailsMapper(genresMapper)

    @Test
    @DisplayName(
        """
            Given a movie details DTO
            When mapping to movie details
            Then result has correct values
        """
    )
    fun toMovieDetailsTest() {
        val result = subject.toMovieDetails(dto = movieDetailsDto)
        assertEquals(expectedMovieDetails, result)
    }
}
