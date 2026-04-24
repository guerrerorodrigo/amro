package com.rodrigoguerrero.ui.home.models

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableSet

@Stable
internal data class TrendingMovie(
    val title: String,
    val imageUrl: String,
    val genres: String?,
    val genreIds: ImmutableSet<Int>,
)
