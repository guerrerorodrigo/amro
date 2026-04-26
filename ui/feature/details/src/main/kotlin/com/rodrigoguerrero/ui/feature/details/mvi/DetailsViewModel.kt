package com.rodrigoguerrero.ui.feature.details.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.rodrigoguerrero.ui.common.mvi.MviViewModel
import com.rodrigoguerrero.ui.feature.details.middlewares.DetailsMiddleware
import com.rodrigoguerrero.ui.feature.details.navigation.DetailsRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailsViewModel @Inject constructor(
    reducer: DetailsReducer,
    detailsMiddleware: DetailsMiddleware,
    savedStateHandle: SavedStateHandle,
) : MviViewModel<DetailsState, DetailsAction>(
    reducer = reducer,
    middlewares = listOf(detailsMiddleware),
    initialState = DetailsState.initial,
) {
    init {
        val movieId = savedStateHandle.toRoute<DetailsRoutes.Details>().id
        handleAction(action = DetailsAction.OnLoad(id = movieId))
    }
}
