package com.rodrigoguerrero.ui.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.theme.components.FullScreenLoader
import com.rodrigoguerrero.theme.components.errors.FullScreenMessage
import com.rodrigoguerrero.theme.components.errors.FullScreenMessageState
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews
import com.rodrigoguerrero.ui.home.components.TrendingMoviesGrid
import com.rodrigoguerrero.ui.home.components.TrendingTopBar
import com.rodrigoguerrero.ui.home.mvi.HomeAction
import com.rodrigoguerrero.ui.home.mvi.HomeState
import com.rodrigoguerrero.ui.home.mvi.HomeViewModel

/**
 * [Composable] that holds the home screen
 * @param onNavigateToDetails lambda executed when tapping on a movie item
 * @param modifier optional [Modifier]
 */
@Composable
fun HomeScreen(
    onNavigateToDetails: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    HomeScreenInternal(modifier = modifier, onMovieClick = onNavigateToDetails)
}

@Composable
internal fun HomeScreenInternal(
    modifier: Modifier = Modifier,
    onMovieClick: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreenContent(
        state = state,
        onAction = viewModel::handleAction,
        modifier = modifier,
        onMovieClick = onMovieClick,
    )
}

@Composable
internal fun HomeScreenContent(
    state: HomeState,
    onMovieClick: (Long) -> Unit,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TrendingTopBar(
                genres = state.genres,
                selectedGenres = state.selectedGenres,
                onAction = onAction,
                sortingOrder = state.sortingOrder,
                sortingType = state.sortingType,
            )
        },
    ) { paddingValues ->
        when {
            state.isLoading -> FullScreenLoader()
            state.fullScreenMessageState != null -> FullScreenMessage(
                onClick = { onAction(HomeAction.OnLoad) },
                state = state.fullScreenMessageState,
            )

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
                onMovieClick = onMovieClick,
            )
        }
    }
}

@WidgetPreviews
@Composable
private fun PreviewHomeScreen(
    @PreviewParameter(HomeParamProvider::class) data: HomeState,
) {
    PreviewBox {
        HomeScreenContent(state = data, onAction = {}, onMovieClick = {})
    }
}
