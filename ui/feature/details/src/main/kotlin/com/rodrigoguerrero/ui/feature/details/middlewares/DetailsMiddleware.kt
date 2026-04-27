package com.rodrigoguerrero.ui.feature.details.middlewares

import com.rodrigoguerrero.domain.details.api.GetMovieDetailsInteractor
import com.rodrigoguerrero.domain.details.api.models.MovieDetails
import com.rodrigoguerrero.ui.common.mvi.Middleware
import com.rodrigoguerrero.ui.feature.details.mappers.DetailsMapper
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsAction
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsAction.OnDataLoaded
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsAction.OnLoad
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsAction.OnLoadFailed
import com.rodrigoguerrero.ui.feature.details.mvi.DetailsState
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class DetailsMiddleware @Inject constructor(
    private val interactor: GetMovieDetailsInteractor,
    private val mapper: DetailsMapper,
) : Middleware<DetailsState, DetailsAction>() {
    override fun process(
        state: DetailsState,
        action: DetailsAction,
    ) {
        when (action) {
            is OnLoad -> loadMovieDetails(id = action.id)
            else -> Unit
        }
    }

    private fun loadMovieDetails(id: Long) {
        scope.launch {
            interactor
                .getMovieDetails(id = id.toString())
                .fold(
                    onSuccess = { processSuccess(it) },
                    onFailure = { dispatch(OnLoadFailed(id)) },
                )
        }
    }

    private fun processSuccess(movieDetails: MovieDetails) {
        val details = mapper.toDetails(details = movieDetails)
        dispatch(OnDataLoaded(details))
    }
}
