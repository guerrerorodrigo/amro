package com.rodrigoguerrero.ui.home.mvi

import com.rodrigoguerrero.ui.common.mvi.MviViewModel
import com.rodrigoguerrero.ui.home.middlewares.HomeMiddleware
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    reducer: HomeReducer,
    middleware: HomeMiddleware,
) : MviViewModel<HomeState, HomeAction>(
    initialState = HomeState.initial,
    reducer = reducer,
    middlewares = listOf(middleware),
) {
    init {
        handleAction(HomeAction.OnLoad)
    }
}
