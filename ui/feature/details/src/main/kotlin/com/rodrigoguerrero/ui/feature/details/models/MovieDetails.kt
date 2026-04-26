package com.rodrigoguerrero.ui.feature.details.models

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
internal data class MovieDetails(
    val title: String,
    val tagline: String,
    val overview: String,
    val budget: String,
    val imdbUrl: String,
    val posterUrl: String,
    val voteAverage: Double,
    val revenue: String,
    val runtime: Int,
    val genres: ImmutableList<String>,
    val voteCount: Int,
    val status: String,
    val releaseDate: String?,
) {
    companion object {
        val initial = MovieDetails(
            title = "",
            tagline = "",
            overview = "",
            budget = "",
            imdbUrl = "",
            posterUrl = "",
            voteAverage = 0.0,
            revenue = "",
            runtime = 0,
            genres = persistentListOf(),
            voteCount = 0,
            status = "",
            releaseDate = null,
        )
    }
}
