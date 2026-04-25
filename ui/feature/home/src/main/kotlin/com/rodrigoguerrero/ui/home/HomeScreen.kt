package com.rodrigoguerrero.ui.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.theme.components.FullScreenLoader
import com.rodrigoguerrero.theme.components.errors.FullScreenMessage
import com.rodrigoguerrero.theme.components.errors.FullScreenMessageState
import com.rodrigoguerrero.ui.home.components.TrendingMoviesGrid
import com.rodrigoguerrero.ui.home.components.TrendingTopBar
import com.rodrigoguerrero.ui.home.mvi.HomeViewModel

/**
 * [Composable] that holds the home screen
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    HomeScreenInternal(modifier = modifier)
}

@Composable
internal fun HomeScreenInternal(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TrendingTopBar(
                genres = state.genres,
                selectedGenres = state.selectedGenres,
                onAction = viewModel::handleAction,
                sortingOrder = state.sortingOrder,
                sortingType = state.sortingType,
            )
        },
    ) { paddingValues ->
        when {
            state.isLoading -> FullScreenLoader()
            state.fullScreenMessageState != null -> state.fullScreenMessageState?.let { errorState ->
                FullScreenMessage(
                    onClick = { }, // TODO implement
                    state = errorState,
                )
            }

            state.filteredTrendingMovies.isEmpty() -> FullScreenMessage(
                state = FullScreenMessageState.LocalFullScreenMessage(
                    messageRes = R.string.there_are_no_results,
                    ctaLabelRes = null,
                )
            )
            else -> TrendingMoviesGrid(
                sortingOrder = state.sortingOrder,
                sortingType = state.sortingType,
                trendingMovies = state.filteredTrendingMovies,
                modifier = Modifier.padding(paddingValues),
            )
        }
    }
}
