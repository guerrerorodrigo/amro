package com.rodrigoguerrero.ui.home.mappers

import com.rodrigoguerrero.ui.home.models.Genre
import com.rodrigoguerrero.ui.home.testdata.domainGenre
import com.rodrigoguerrero.ui.home.testdata.expectedGenre
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import com.rodrigoguerrero.domain.home.api.models.Genre as DomainGenre

internal class GenreMapperTest {
    private val subject = GenreMapper()

    @ParameterizedTest(name = "genres={0}, expected={1}")
    @MethodSource("provideTestData")
    @DisplayName(
        """
            Given a list of domain genres
            When mapping toGenres
            Then expected result has the correct values
        """
    )
    fun toGenresTest(
        genres: List<DomainGenre>,
        expected: List<Genre>,
    ) {
        val result = subject.toGenres(genres)
        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        fun provideTestData() = listOf(
            Arguments.arguments(listOf(domainGenre), listOf(expectedGenre)),
            Arguments.arguments(listOf<DomainGenre>(), listOf<Genre>()),
        )
    }
}
