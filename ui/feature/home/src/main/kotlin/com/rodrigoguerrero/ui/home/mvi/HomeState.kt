package com.rodrigoguerrero.ui.home.mvi

import androidx.compose.runtime.Stable
import com.rodrigoguerrero.theme.components.errors.FullScreenMessageState
import com.rodrigoguerrero.ui.common.mvi.State
import com.rodrigoguerrero.ui.home.models.Genre
import com.rodrigoguerrero.ui.home.models.SortingOrder
import com.rodrigoguerrero.ui.home.models.SortingType
import com.rodrigoguerrero.ui.home.models.TrendingMovie
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

@Stable
internal data class HomeState(
    val isLoading: Boolean,
    val trendingMovies: ImmutableList<TrendingMovie>,
    val filteredTrendingMovies: ImmutableList<TrendingMovie>,
    val genres: ImmutableList<Genre>,
    val fullScreenMessageState: FullScreenMessageState?,
    val selectedGenres: ImmutableSet<Int>,
    val sortingType: SortingType,
    val sortingOrder: SortingOrder,
) : State {
    companion object {
        val initial = HomeState(
            trendingMovies = persistentListOf(),
            genres = persistentListOf(),
            selectedGenres = persistentSetOf(),
            filteredTrendingMovies = persistentListOf(),
            isLoading = true,
            fullScreenMessageState = null,
            sortingType = SortingType.Popularity,
            sortingOrder = SortingOrder.Descending,
        )
    }
}
