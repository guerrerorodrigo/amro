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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.theme.components.FullScreenLoader
import com.rodrigoguerrero.theme.components.errors.FullScreenMessage
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews
import com.rodrigoguerrero.ui.feature.details.components.MovieDetails
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsAction
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
    MovieDetailsContent(
        state = state,
        modifier = modifier,
        onBack = onBack,
        onAction = viewModel::handleAction,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MovieDetailsContent(
    state: DetailsState,
    onBack: () -> Unit,
    onAction: (DetailsAction) -> Unit,
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
                onClick = state.id?.let { id ->
                    { onAction(DetailsAction.OnLoad(id)) }
                }
            )

            else -> MovieDetails(
                modifier = Modifier.padding(paddingValues),
                data = state.movieDetails,
                onBack = onBack,
            )
        }
    }
}

@WidgetPreviews
@Composable
private fun PreviewMovieDetailsScreen(
    @PreviewParameter(MovieDetailsParamProvider::class) state: DetailsState,
) {
    PreviewBox {
        MovieDetailsContent(
            state = state,
            onBack = {},
            onAction = {},
        )
    }
}
