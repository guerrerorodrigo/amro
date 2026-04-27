package com.rodrigoguerrero.ui.feature.details

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rodrigoguerrero.theme.components.errors.FullScreenMessageState
import com.rodrigoguerrero.ui.feature.details.components.testMovieDetails
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsState

internal val testMovieDetailsState = DetailsState(
    isLoading = false,
    movieDetails = testMovieDetails,
    fullScreenMessageState = null,
    id = 123,
)

internal val testLoadingMovieDetailsState = DetailsState.initial

internal val testErrorMovieDetailsState = DetailsState.initial.copy(
    isLoading = false,
    fullScreenMessageState = FullScreenMessageState.LocalFullScreenMessage(
        messageRes = R.string.loading_error,
        ctaLabelRes = R.string.try_again,
    ),
    id = 123,
)

internal class MovieDetailsParamProvider : PreviewParameterProvider<DetailsState> {
    override val values: Sequence<DetailsState>
        get() = sequenceOf(
            testMovieDetailsState,
            testLoadingMovieDetailsState,
            testErrorMovieDetailsState,
        )
}
