package com.rodrigoguerrero.ui.feature.details.mvi

import com.rodrigoguerrero.theme.components.errors.FullScreenMessageState
import com.rodrigoguerrero.ui.common.mvi.Reducer
import com.rodrigoguerrero.ui.feature.details.R
import com.rodrigoguerrero.ui.feature.details.models.MovieDetails
import javax.inject.Inject

internal class DetailsReducer @Inject constructor() : Reducer<DetailsState, DetailsAction> {
    override fun reduce(
        state: DetailsState,
        action: DetailsAction,
    ): DetailsState = when (action) {
        is DetailsAction.OnLoad -> state.copy(
            isLoading = true,
            movieDetails = MovieDetails.initial,
            fullScreenMessageState = null,
        )
        is DetailsAction.OnDataLoaded -> state.copy(
            isLoading = false,
            movieDetails = action.movieDetails,
            fullScreenMessageState = null,
        )
        is DetailsAction.OnLoadFailed -> state.copy(
            id = action.id,
            isLoading = false,
            movieDetails = MovieDetails.initial,
            fullScreenMessageState = FullScreenMessageState.LocalFullScreenMessage(
                messageRes = R.string.loading_error,
                ctaLabelRes = R.string.try_again,
            )
        )
    }
}
