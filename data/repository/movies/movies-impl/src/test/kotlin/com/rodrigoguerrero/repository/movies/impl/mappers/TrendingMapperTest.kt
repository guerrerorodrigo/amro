package com.rodrigoguerrero.repository.movies.impl.mappers

import com.rodrigoguerrero.data.source.tmdb.api.models.PageResultDto
import com.rodrigoguerrero.data.source.tmdb.api.models.TrendingDto
import com.rodrigoguerrero.repository.movies.api.models.Trending
import com.rodrigoguerrero.repository.movies.impl.testdata.emptyTrendingPageResultDto
import com.rodrigoguerrero.repository.movies.impl.testdata.expectedTrending
import com.rodrigoguerrero.repository.movies.impl.testdata.trendingPageResultDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class TrendingMapperTest {
    private val subject = TrendingMapper()

    @ParameterizedTest(name = "dto={0}, expected={1}")
    @MethodSource("provideToTrendingTestData")
    @DisplayName(
        """
            Given a PageResultDto with trendingDtos
            When mapping to trending
            Then trending result contains correct data
        """
    )
    fun toTrendingTest(
        dto: PageResultDto<TrendingDto>,
        expected: List<Trending>,
    ) {
        val result = subject.toTrending(dto)
        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        fun provideToTrendingTestData() = listOf(
            Arguments.arguments(trendingPageResultDto, listOf(expectedTrending)),
            Arguments.arguments(emptyTrendingPageResultDto, listOf<Trending>()),
        )
    }
}
