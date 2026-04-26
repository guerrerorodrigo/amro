package com.rodrigoguerrero.ui.home.testdata

import com.rodrigoguerrero.ui.home.mvi.HomeState
import kotlinx.collections.immutable.persistentListOf

internal val trendingMovies = persistentListOf(expectedTrendingMovie, uiTrendingMovie, anotherUiTrendingMovie)

internal val homeState = HomeState.initial.copy(
    isLoading = false,
    trendingMovies = trendingMovies,
    filteredTrendingMovies = trendingMovies,
    genres = uiGenres,
)
