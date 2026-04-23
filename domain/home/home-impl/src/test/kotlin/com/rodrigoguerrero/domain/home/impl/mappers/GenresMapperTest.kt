package com.rodrigoguerrero.domain.home.impl.mappers

import com.rodrigoguerrero.domain.home.api.models.Genre
import com.rodrigoguerrero.domain.home.impl.testdata.anotherExpectedGenre
import com.rodrigoguerrero.domain.home.impl.testdata.anotherGenre
import com.rodrigoguerrero.domain.home.impl.testdata.expectedGenre
import com.rodrigoguerrero.domain.home.impl.testdata.genre
import com.rodrigoguerrero.domain.home.impl.testdata.genres
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class GenresMapperTest {
    private val subject = GenresMapper()

    @Test
    @DisplayName(
        """
            Given a list of genres from repository
            When mapping to genres
            Then correct domain genres are returned
        """
    )
    fun toGenreTest() {
        val result = subject.toGenres(genres = listOf(genre, anotherGenre))
        assertEquals(listOf(expectedGenre, anotherExpectedGenre), result)
    }

    @ParameterizedTest(name = "ids={0}, genres={1}, expected={2}")
    @MethodSource("provideFilterTestData")
    @DisplayName(
        """
            Given a list of genre ids and genres
            When calling filterGenresByIds
            Then filtered genres are returned
        """
    )
    fun filterGenresByIdsTest(
        ids: List<Int>,
        genres: List<Genre>,
        expected: List<Genre>,
    ) {
        val result = subject.filterGenresByIds(genreIds = ids, genres = genres)
        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        fun provideFilterTestData() = listOf(
            Arguments.arguments(listOf(1, 2), genres, listOf(expectedGenre, anotherExpectedGenre)),
            Arguments.arguments(listOf(3, 4), genres, listOf<Genre>()),
            Arguments.arguments(listOf(1, 2), listOf<Genre>(), listOf<Genre>()),
        )
    }
}
