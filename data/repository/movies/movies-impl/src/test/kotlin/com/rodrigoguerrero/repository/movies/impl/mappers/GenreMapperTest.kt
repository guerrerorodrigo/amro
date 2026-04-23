package com.rodrigoguerrero.repository.movies.impl.mappers

import com.rodrigoguerrero.data.source.tmdb.api.models.GenreDto
import com.rodrigoguerrero.repository.movies.api.models.Genre
import com.rodrigoguerrero.repository.movies.impl.testdata.anotherExpectedGenre
import com.rodrigoguerrero.repository.movies.impl.testdata.anotherGenreDto
import com.rodrigoguerrero.repository.movies.impl.testdata.expectedGenre
import com.rodrigoguerrero.repository.movies.impl.testdata.genreDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class GenreMapperTest {
    private val subject = GenreMapper()

    @ParameterizedTest(name = "dtos={0}, expected={1}")
    @MethodSource("provideToGenresTestData")
    @DisplayName(
        """
            Given a list of GenreDtos
            When mapping toGenres
            Then mapped genres have the correct values
        """
    )
    fun toGenresTest(
        dtos: List<GenreDto>,
        expected: List<Genre>,
    ) {
        val result = subject.toGenres(dtos)
        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        fun provideToGenresTestData() = listOf(
            Arguments.arguments(listOf(genreDto, anotherGenreDto), listOf(
                expectedGenre,
                anotherExpectedGenre
            )),
            Arguments.arguments(listOf<GenreDto>(), listOf<Genre>()),
        )
    }
}
