package com.rodrigoguerrero.ui.home.components

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rodrigoguerrero.ui.home.models.TrendingMovie
import kotlinx.collections.immutable.persistentSetOf

internal val testTrendingMovie = TrendingMovie(
    id = 123,
    title = "Title",
    genres = "Genre / Genre",
    imageUrl = "",
    genreIds = persistentSetOf(),
    popularity = 123.55,
    releaseDate = 123L,
)

internal class MovieItemParamProvider : PreviewParameterProvider<TrendingMovie> {
    override val values: Sequence<TrendingMovie>
        get() = sequenceOf(testTrendingMovie)
}
