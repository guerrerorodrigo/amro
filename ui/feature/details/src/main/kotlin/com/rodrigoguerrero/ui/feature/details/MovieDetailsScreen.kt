package com.rodrigoguerrero.ui.feature.details

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.theme.components.FullScreenLoader
import com.rodrigoguerrero.theme.components.errors.FullScreenMessage
import com.rodrigoguerrero.ui.feature.details.components.MovieDetails
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsState
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsViewModel

@Composable
fun MovieDetailsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MovieDetailsScreenInternal(modifier = modifier, onBack = onBack)
}

@Composable
internal fun MovieDetailsScreenInternal(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MovieDetailsContent(state = state, modifier = modifier, onBack = onBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MovieDetailsContent(
    state: DetailsState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        when {
            state.isLoading -> FullScreenLoader()
            state.fullScreenMessageState != null -> FullScreenMessage(
                state = state.fullScreenMessageState,
            )

            else -> MovieDetails(
                modifier = Modifier.padding(paddingValues),
                data = state.movieDetails,
                onBack = onBack,
            )
        }
    }
}
