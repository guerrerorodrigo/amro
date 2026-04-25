package com.rodrigoguerrero.ui.home.components

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rodrigoguerrero.ui.home.models.TrendingMovie
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

private val movie = TrendingMovie(
    id = 1,
    title = "title",
    imageUrl = "",
    genres = "Genres",
    genreIds = persistentSetOf(),
    popularity = 123.0,
    releaseDate = 1000000L,
)

internal val testTrendingMovies = persistentListOf(
    movie,
    movie.copy(id = 2),
    movie.copy(id = 3),
)

internal class TrendingMoviesGridParamProvider : PreviewParameterProvider<ImmutableList<TrendingMovie>> {
    override val values: Sequence<ImmutableList<TrendingMovie>>
        get() = sequenceOf(testTrendingMovies)
}
