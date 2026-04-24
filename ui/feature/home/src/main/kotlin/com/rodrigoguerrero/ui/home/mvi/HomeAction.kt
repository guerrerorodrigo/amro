package com.rodrigoguerrero.ui.home.mvi

import com.rodrigoguerrero.ui.common.mvi.MviAction
import com.rodrigoguerrero.ui.home.models.Genre
import com.rodrigoguerrero.ui.home.models.TrendingMovie
import kotlinx.collections.immutable.ImmutableList

internal sealed interface HomeAction : MviAction {
    data object OnLoad : HomeAction
    data class OnDataLoaded(
        val trendingMovies: ImmutableList<TrendingMovie>,
        val genres: ImmutableList<Genre>,
    ) : HomeAction

    data class OnGenreTapped(val id: Int) : HomeAction
}
