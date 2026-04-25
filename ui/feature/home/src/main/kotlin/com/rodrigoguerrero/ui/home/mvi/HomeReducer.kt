package com.rodrigoguerrero.ui.home.mvi

import com.rodrigoguerrero.theme.components.errors.FullScreenMessageState
import com.rodrigoguerrero.ui.common.mvi.Reducer
import com.rodrigoguerrero.ui.home.R
import com.rodrigoguerrero.ui.home.models.SortingOrder
import com.rodrigoguerrero.ui.home.models.SortingType
import com.rodrigoguerrero.ui.home.models.TrendingMovie
import com.rodrigoguerrero.ui.home.mvi.HomeAction.OnDataLoaded
import com.rodrigoguerrero.ui.home.mvi.HomeAction.OnSortOrderTapped
import com.rodrigoguerrero.ui.home.mvi.HomeAction.OnSortTypeChanged
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.toPersistentSet
import javax.inject.Inject

internal class HomeReducer @Inject constructor() : Reducer<HomeState, HomeAction> {
    override fun reduce(
        state: HomeState,
        action: HomeAction,
    ): HomeState = when (action) {
        HomeAction.OnLoad -> state.copy(
            trendingMovies = persistentListOf(),
            genres = persistentListOf(),
            selectedGenres = persistentSetOf(),
            filteredTrendingMovies = persistentListOf(),
            isLoading = true,
            fullScreenMessageState = null,
        )

        is OnDataLoaded -> {
            val sortedMovies = action.trendingMovies.sort(
                sortingType = state.sortingType,
                sortingOrder = state.sortingOrder,
            )
            state.copy(
                isLoading = false,
                filteredTrendingMovies = sortedMovies,
                trendingMovies = action.trendingMovies,
                genres = action.genres,
            )
        }

        is HomeAction.OnGenreTapped -> {
            val updatedGenres = state.updateSelectedGenres(id = action.id).toPersistentSet()
            state.copy(
                selectedGenres = updatedGenres,
                filteredTrendingMovies = state.getFilteredTrendingMovies(updatedGenres = updatedGenres)
            )
        }

        is OnSortOrderTapped -> {
            val sortingOrder = if (state.sortingOrder == SortingOrder.Ascending) {
                SortingOrder.Descending
            } else {
                SortingOrder.Ascending
            }
            state.copy(
                sortingOrder = sortingOrder,
                filteredTrendingMovies = state.filteredTrendingMovies.sort(
                    sortingType = state.sortingType,
                    sortingOrder = sortingOrder,
                ),
            )
        }

        is OnSortTypeChanged -> state.copy(
            sortingType = action.sortingType,
            filteredTrendingMovies = state.filteredTrendingMovies.sort(
                sortingType = action.sortingType,
                sortingOrder = state.sortingOrder,
            ),
        )

        is HomeAction.OnDataLoadFailed -> state.copy(
            fullScreenMessageState = FullScreenMessageState.LocalFullScreenMessage(
                messageRes = R.string.loading_error,
                ctaLabelRes = R.string.try_again,
            ),
            isLoading = false,
        )
    }

    private fun ImmutableList<TrendingMovie>.sort(
        sortingType: SortingType,
        sortingOrder: SortingOrder,
    ): ImmutableList<TrendingMovie> {
        val sortingComparator = sortingType.comparator()
        val comparator = if (sortingOrder == SortingOrder.Ascending) {
            sortingComparator
        } else {
            sortingComparator.reversed()
        }
        return sortedWith(comparator).toImmutableList()
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
