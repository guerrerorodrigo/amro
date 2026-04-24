package com.rodrigoguerrero.ui.home.mvi

import com.rodrigoguerrero.ui.common.mvi.Reducer
import javax.inject.Inject

internal class HomeReducer @Inject constructor() : Reducer<HomeState, HomeAction> {
    override fun reduce(
        state: HomeState,
        action: HomeAction,
    ): HomeState = when (action) {

        else -> state
    }
}
