package com.rodrigoguerrero.ui.feature.details.mvi

import androidx.compose.runtime.Stable
import com.rodrigoguerrero.theme.components.errors.FullScreenMessageState
import com.rodrigoguerrero.ui.common.mvi.State
import com.rodrigoguerrero.ui.feature.details.models.MovieDetails

@Stable
internal data class DetailsState(
    val isLoading: Boolean,
    val movieDetails: MovieDetails,
    val fullScreenMessageState: FullScreenMessageState?,
) : State {
    companion object {
        val initial = DetailsState(
            isLoading = true,
            movieDetails = MovieDetails.initial,
            fullScreenMessageState = null,
        )
    }
}
