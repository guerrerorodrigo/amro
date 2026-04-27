package com.rodrigoguerrero.ui.feature.details.mvi

import com.rodrigoguerrero.ui.common.mvi.MviAction
import com.rodrigoguerrero.ui.feature.details.models.MovieDetails

internal sealed interface DetailsAction : MviAction {
    data class OnLoad(val id: Long) : DetailsAction
    data class OnDataLoaded(val movieDetails: MovieDetails) : DetailsAction
    data class OnLoadFailed(val id: Long) : DetailsAction
}
