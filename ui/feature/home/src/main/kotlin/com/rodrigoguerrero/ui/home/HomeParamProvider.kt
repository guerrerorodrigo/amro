package com.rodrigoguerrero.ui.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rodrigoguerrero.theme.components.errors.FullScreenMessageState
import com.rodrigoguerrero.ui.home.components.testGenres
import com.rodrigoguerrero.ui.home.components.testTrendingMovies
import com.rodrigoguerrero.ui.home.models.SortingOrder
import com.rodrigoguerrero.ui.home.models.SortingType
import com.rodrigoguerrero.ui.home.mvi.HomeState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

internal val testHomeState = HomeState(
    isLoading = false,
    trendingMovies = testTrendingMovies,
    filteredTrendingMovies = testTrendingMovies,
    genres = testGenres,
    fullScreenMessageState = null,
    selectedGenres = persistentSetOf(1),
    sortingOrder = SortingOrder.Descending,
    sortingType = SortingType.Title,
)

internal val testHomeEmptyState = HomeState(
    isLoading = false,
    trendingMovies = persistentListOf(),
    filteredTrendingMovies = persistentListOf(),
    genres = testGenres,
    fullScreenMessageState = null,
    selectedGenres = persistentSetOf(1),
    sortingOrder = SortingOrder.Descending,
    sortingType = SortingType.Title,
)

internal val testHomeLoadingState = HomeState(
    isLoading = true,
    trendingMovies = persistentListOf(),
    filteredTrendingMovies = persistentListOf(),
    genres = persistentListOf(),
    fullScreenMessageState = null,
    selectedGenres = persistentSetOf(),
    sortingType = SortingType.Popularity,
    sortingOrder = SortingOrder.Descending,
)

internal val testErrorHomeState = HomeState(
    isLoading = false,
    trendingMovies = persistentListOf(),
    filteredTrendingMovies = persistentListOf(),
    genres = persistentListOf(),
    fullScreenMessageState = FullScreenMessageState.LocalFullScreenMessage(
        messageRes = R.string.loading_error,
        ctaLabelRes = R.string.try_again,
    ),
    selectedGenres = persistentSetOf(),
    sortingType = SortingType.Popularity,
    sortingOrder = SortingOrder.Descending,
)

internal class HomeParamProvider : PreviewParameterProvider<HomeState> {
    override val values: Sequence<HomeState>
        get() = sequenceOf(
            testHomeState,
            testHomeEmptyState,
            testHomeLoadingState,
            testErrorHomeState,
        )
}
