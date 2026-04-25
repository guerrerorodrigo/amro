package com.rodrigoguerrero.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews
import com.rodrigoguerrero.ui.home.models.SortingOrder
import com.rodrigoguerrero.ui.home.models.SortingType
import com.rodrigoguerrero.ui.home.models.TrendingMovie
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

@Composable
internal fun TrendingMoviesGrid(
    sortingOrder: SortingOrder,
    sortingType: SortingType,
    trendingMovies: ImmutableList<TrendingMovie>,
    modifier: Modifier = Modifier,
) {
    val gridState = rememberLazyGridState()
    LaunchedEffect(sortingOrder, sortingType) {
        gridState.scrollToItem(0)
    }

    LazyVerticalGrid(
        state = gridState,
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(AmroTheme.dimens.padding.md),
        horizontalArrangement = Arrangement.spacedBy(AmroTheme.dimens.padding.md),
        contentPadding = PaddingValues(
            end = AmroTheme.dimens.padding.md,
            start = AmroTheme.dimens.padding.md,
            bottom = AmroTheme.dimens.padding.md,
        ),
        columns = GridCells.Adaptive(130.dp),
    ) {
        items(items = trendingMovies, key = { it.id }) { item ->
            MovieItem(data = item)
        }
    }
}

@WidgetPreviews
@Composable
private fun PreviewTrendingMoviesGrid() {
    val movie = TrendingMovie(
        id = 1,
        title = "title",
        imageUrl = "",
        genres = "Genres",
        genreIds = persistentSetOf(),
        popularity = 123.0,
        releaseDate = 1000000L,
    )
    PreviewBox {
        TrendingMoviesGrid(
            sortingType = SortingType.Popularity,
            sortingOrder = SortingOrder.Descending,
            trendingMovies = persistentListOf(
                movie,
                movie.copy(id = 2),
                movie.copy(id = 3),
            ),
        )
    }
}