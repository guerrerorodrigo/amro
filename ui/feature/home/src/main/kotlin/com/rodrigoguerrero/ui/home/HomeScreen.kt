package com.rodrigoguerrero.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.components.FullScreenLoader
import com.rodrigoguerrero.theme.components.errors.FullScreenMessage
import com.rodrigoguerrero.ui.home.components.MovieItem
import com.rodrigoguerrero.ui.home.components.TrendingFilter
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
            TrendingFilter(
                genres = state.genres,
                selectedGenres = state.selectedGenres,
                onAction = viewModel::handleAction,
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

            // TODO: show empty message
            else -> LazyVerticalGrid(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(AmroTheme.dimens.padding.md),
                horizontalArrangement = Arrangement.spacedBy(AmroTheme.dimens.padding.md),
                contentPadding = PaddingValues(
                    end = AmroTheme.dimens.padding.md,
                    start = AmroTheme.dimens.padding.md,
                    bottom = AmroTheme.dimens.padding.md,
                ),
                columns = GridCells.Adaptive(130.dp),
            ) {
                items(items = state.filteredTrendingMovies) { item ->
                    MovieItem(data = item)
                }
            }
        }
    }
}
