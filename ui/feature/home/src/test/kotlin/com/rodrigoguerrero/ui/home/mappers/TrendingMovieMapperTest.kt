package com.rodrigoguerrero.ui.home.mappers

import com.rodrigoguerrero.ui.home.models.TrendingMovie
import com.rodrigoguerrero.ui.home.testdata.domainTrendingMovie
import com.rodrigoguerrero.ui.home.testdata.expectedGenre
import com.rodrigoguerrero.ui.home.testdata.expectedTrendingMovie
import io.mockk.every
import com.rodrigoguerrero.domain.home.api.models.TrendingMovie as DomainTrendingMovie
import io.mockk.mockk
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class TrendingMovieMapperTest {
    private val genreMapper = mockk<GenreMapper> {
        every { toGenres(any()) } returns persistentListOf(
            expectedGenre,
            expectedGenre.copy(id = 2, name = "another genre"),
        )
    }
    private val subject = TrendingMovieMapper(
        genreMapper = genreMapper,
    )

    @ParameterizedTest(name = "domainTrendingMovies={0}, expected={1}")
    @MethodSource("provideTestData")
    @DisplayName(
        """
            Given a list of domain trending movies
            When calling toTrendingMovies
            Then expected UI trending movies have correct data
        """
    )
    fun toTrendingMoviesTest(
        domainTrendingMovies: List<DomainTrendingMovie>,
        expected: ImmutableList<TrendingMovie>,
    ) {
        val result = subject.toTrendingMovies(domainTrendingMovies)
        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        fun provideTestData() = listOf(
            Arguments.arguments(listOf(domainTrendingMovie), persistentListOf(expectedTrendingMovie)),
            Arguments.arguments(listOf<DomainTrendingMovie>(), persistentListOf<TrendingMovie>()),
        )
    }
}
