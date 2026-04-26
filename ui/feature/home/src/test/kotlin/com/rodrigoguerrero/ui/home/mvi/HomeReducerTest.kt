package com.rodrigoguerrero.ui.home.mvi

import com.rodrigoguerrero.theme.components.errors.FullScreenMessageState
import com.rodrigoguerrero.ui.home.R
import com.rodrigoguerrero.ui.home.models.SortingOrder
import com.rodrigoguerrero.ui.home.models.SortingType
import com.rodrigoguerrero.ui.home.models.TrendingMovie
import com.rodrigoguerrero.ui.home.testdata.homeState
import com.rodrigoguerrero.ui.home.testdata.trendingMovies
import com.rodrigoguerrero.ui.home.testdata.trendingMoviesSortedByPopularityAscending
import com.rodrigoguerrero.ui.home.testdata.trendingMoviesSortedByPopularityDescending
import com.rodrigoguerrero.ui.home.testdata.trendingMoviesSortedByReleaseDateAscending
import com.rodrigoguerrero.ui.home.testdata.trendingMoviesSortedByReleaseDateDescending
import com.rodrigoguerrero.ui.home.testdata.trendingMoviesSortedByTitleAscending
import com.rodrigoguerrero.ui.home.testdata.trendingMoviesSortedByTitleDescending
import com.rodrigoguerrero.ui.home.testdata.uiGenres
import io.mockk.mockk
import kotlinx.collections.immutable.ImmutableList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertNull
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class HomeReducerTest {
    private val subject = HomeReducer()

    @Test
    @DisplayName(
        """
            Given a home state with data
            When reducing the OnLoad action
            Then all data is cleared, isLoading is true, and fullScreenMessageState is cleared
        """
    )
    fun onLoadTest() {
        val result = subject.reduce(
            state = homeState.copy(fullScreenMessageState = mockk()),
            action = HomeAction.OnLoad,
        )

        assertAll(
            { assertEquals(0, result.trendingMovies.size) },
            { assertEquals(0, result.filteredTrendingMovies.size) },
            { assertEquals(0, result.genres.size) },
            { assertEquals(0, result.selectedGenres.size) },
            { assertTrue(result.isLoading) },
            { assertNull(result.fullScreenMessageState) },
        )
    }

    @Test
    @DisplayName(
        """
            Given an initial state with no data
            When reducing OnDataLoaded action
            Then state has data and filteredTrendingMovies are sorted by the provided sorting type and order
        """
    )
    fun onDataLoadedTest() {
        val result = subject.reduce(
            state = HomeState.initial.copy(
                sortingType = SortingType.Title,
                sortingOrder = SortingOrder.Ascending,
            ),
            action = HomeAction.OnDataLoaded(
                trendingMovies = trendingMovies,
                genres = uiGenres,
            ),
        )

        assertAll(
            { assertEquals("another title", result.filteredTrendingMovies[0].title) },
            { assertEquals("movie title", result.filteredTrendingMovies[1].title) },
            { assertEquals("title", result.filteredTrendingMovies[2].title) },
            { assertEquals(trendingMovies, result.trendingMovies) },
            { assertFalse(result.isLoading) },
            { assertEquals(uiGenres, result.genres) },
        )
    }

    @Test
    @DisplayName(
        """
            Given a home state with trending movies
            When reducing the OnGenreTapped
            Then movies with provided genre are selected
        """
    )
    fun onGenreTappedTest() {
        val genreIdTwoSelectedResult = subject.reduce(
            state = homeState,
            action = HomeAction.OnGenreTapped(id = 2),
        )
        val genreIdTwoAndThreeSelectedResult = subject.reduce(
            state = genreIdTwoSelectedResult,
            action = HomeAction.OnGenreTapped(id = 3),
        )
        val clearGenreIdTwoResult = subject.reduce(
            state = genreIdTwoSelectedResult,
            action = HomeAction.OnGenreTapped(id = 2),
        )

        assertAll(
            { assertEquals(1, genreIdTwoSelectedResult.filteredTrendingMovies.size) },
            { assertEquals(1, genreIdTwoSelectedResult.filteredTrendingMovies[0].id) },
            { assertEquals(2, genreIdTwoAndThreeSelectedResult.filteredTrendingMovies.size) },
            { assertEquals(1, genreIdTwoAndThreeSelectedResult.filteredTrendingMovies[0].id) },
            { assertEquals(3, genreIdTwoAndThreeSelectedResult.filteredTrendingMovies[1].id) },
            { assertEquals(homeState, clearGenreIdTwoResult)},
        )
    }

    @ParameterizedTest(name = "previousSortOrder={0}, sortingType={1}, expectedFilteredMovies={2}")
    @MethodSource("provideSortOrderData")
    @DisplayName(
        """
            Given a home state with filtered data with the provided sorting order and sorting type
            When OnSortOrderTapped is reduced
            Then filteredTrendingMovies has the correct sorting order
        """
    )
    fun onSortOrderTapped(
        previousSortOrder: SortingOrder,
        sortingType: SortingType,
        expectedFilteredMovies: ImmutableList<TrendingMovie>,
    ) {
        val result = subject.reduce(
            state = homeState.copy(
                sortingOrder = previousSortOrder,
                sortingType = sortingType,
            ),
            action = HomeAction.OnSortOrderTapped,
        )

        assertEquals(expectedFilteredMovies, result.filteredTrendingMovies)
    }

    @ParameterizedTest(name = "sortingType={0}, expectedFilteredMovies={1}")
    @MethodSource("provideSortTypeData")
    @DisplayName(
        """
            Given an initial state with filtered movies
            When OnSortTypeChanged action is reduced
            Then filteredTrendingMovies is sorted by the provided sorting type
        """
    )
    fun onSortTypeChangedTapped(
        sortingType: SortingType,
        expectedFilteredMovies: ImmutableList<TrendingMovie>,
    ) {
        val result = subject.reduce(
            state = homeState,
            action = HomeAction.OnSortTypeChanged(sortingType = sortingType),
        )

        assertEquals(expectedFilteredMovies, result.filteredTrendingMovies)
    }

    @Test
    fun onDataLoadFailedTest() {
        val result = subject.reduce(
            state = HomeState.initial,
            action = HomeAction.OnDataLoadFailed,
        )

        assertAll(
            { assertFalse(result.isLoading) },
            { assertNotNull(result.fullScreenMessageState) },
            {
                assertEquals(
                    R.string.loading_error,
                    (result.fullScreenMessageState as? FullScreenMessageState.LocalFullScreenMessage)?.messageRes,
                )
            },
            {
                assertEquals(
                    R.string.try_again,
                    result.fullScreenMessageState?.ctaLabelRes,
                )
            },
        )
    }

    companion object {
        @JvmStatic
        fun provideSortOrderData() = listOf(
            Arguments.arguments(SortingOrder.Ascending, SortingType.Title, trendingMoviesSortedByTitleDescending),
            Arguments.arguments(SortingOrder.Descending, SortingType.Title, trendingMoviesSortedByTitleAscending),
            Arguments.arguments(SortingOrder.Ascending, SortingType.Popularity, trendingMoviesSortedByPopularityDescending),
            Arguments.arguments(SortingOrder.Descending, SortingType.Popularity, trendingMoviesSortedByPopularityAscending),
            Arguments.arguments(SortingOrder.Ascending, SortingType.ReleaseDate, trendingMoviesSortedByReleaseDateDescending),
            Arguments.arguments(SortingOrder.Descending, SortingType.ReleaseDate, trendingMoviesSortedByReleaseDateAscending),
        )

        @JvmStatic
        fun provideSortTypeData() = listOf(
            Arguments.arguments(SortingType.Title, trendingMoviesSortedByTitleDescending),
            Arguments.arguments(SortingType.Popularity, trendingMoviesSortedByPopularityDescending),
            Arguments.arguments(SortingType.ReleaseDate, trendingMoviesSortedByReleaseDateDescending),
        )
    }
}
