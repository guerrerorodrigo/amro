package com.rodrigoguerrero.ui.home.mvi

import androidx.compose.runtime.Stable
import com.rodrigoguerrero.ui.common.mvi.State
import com.rodrigoguerrero.ui.home.models.TrendingMovie
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
internal data class HomeState(
    val isLoading: Boolean,
    val trendingMovies: ImmutableList<TrendingMovie>,
) : State {
    companion object {
        val initial = HomeState(
            trendingMovies = persistentListOf(),
            isLoading = true,
        )
    }
}
