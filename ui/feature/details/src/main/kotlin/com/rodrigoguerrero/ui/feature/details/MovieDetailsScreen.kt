package com.rodrigoguerrero.ui.feature.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsState
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsViewModel

@Composable
fun MovieDetailsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MovieDetailsScreenInternal(modifier = modifier)
}

@Composable
internal fun MovieDetailsScreenInternal(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MovieDetailsContent(state = state, modifier = modifier)
}

@Composable
internal fun MovieDetailsContent(
    state: DetailsState,
    modifier: Modifier = Modifier,
) {

}
