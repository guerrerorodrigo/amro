package com.rodrigoguerrero.ui.home.mvi

import com.rodrigoguerrero.ui.common.mvi.Reducer
import com.rodrigoguerrero.ui.home.models.TrendingMovie
import com.rodrigoguerrero.ui.home.mvi.HomeAction.OnDataLoaded
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.toPersistentSet
import javax.inject.Inject

internal class HomeReducer @Inject constructor() : Reducer<HomeState, HomeAction> {
    override fun reduce(
        state: HomeState,
        action: HomeAction,
    ): HomeState = when (action) {
        is OnDataLoaded -> state.copy(
            isLoading = false,
            trendingMovies = action.trendingMovies,
            filteredTrendingMovies = action.trendingMovies,
            genres = action.genres,
        )

        is HomeAction.OnGenreTapped -> {
            val updatedGenres = state.updateSelectedGenres(id = action.id).toPersistentSet()
            state.copy(
                selectedGenres = updatedGenres,
                filteredTrendingMovies = state.getFilteredTrendingMovies(updatedGenres = updatedGenres)
            )
        }

        else -> state
    }

    private fun HomeState.getFilteredTrendingMovies(
        updatedGenres: PersistentSet<Int>,
    ): ImmutableList<TrendingMovie> = if (updatedGenres.isEmpty()) {
        trendingMovies
    } else {
        trendingMovies.filter { movie ->
            movie.genreIds.any { it in updatedGenres }
        }.toPersistentList()
    }

    private fun HomeState.updateSelectedGenres(id: Int) = if (selectedGenres.contains(id)) {
        selectedGenres.filterNot { id == it }
    } else {
        selectedGenres + id
    }
}
