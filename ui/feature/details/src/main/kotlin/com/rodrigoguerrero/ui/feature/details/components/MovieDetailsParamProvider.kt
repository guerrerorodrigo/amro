package com.rodrigoguerrero.ui.feature.details.components

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rodrigoguerrero.ui.feature.details.models.MovieDetails

internal val testMovieDetails = MovieDetails(
    title = "title",
    tagline = "tagline",
    overview = "overview",
    budget = "$5000000",
    imdbUrl = "",
    posterUrl = "",
    voteAverage = 8.4,
    revenue = "$33330000",
    runtime = 98,
    genres = testGenres,
    voteCount = 12345,
    status = "released",
    releaseDate = "May 31, 2017",
)

internal class MovieDetailsParamProvider : PreviewParameterProvider<MovieDetails> {
    override val values: Sequence<MovieDetails>
        get() = sequenceOf(testMovieDetails)
}
