package com.rodrigoguerrero.ui.home.components

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rodrigoguerrero.ui.home.models.Genre
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

internal val testActionGenre = Genre(id = 1, name = "Action")
internal val testComedyGenre = Genre(id = 2, name = "Comedy")
internal val testGenres = persistentListOf(testActionGenre, testComedyGenre)

internal class TrendingFilterParamProvider : PreviewParameterProvider<Pair<ImmutableList<Genre>, ImmutableSet<Int>>> {
    override val values: Sequence<Pair<ImmutableList<Genre>, ImmutableSet<Int>>>
        get() = sequenceOf(
            Pair(testGenres, persistentSetOf(1)),
            Pair(testGenres, persistentSetOf(1, 2)),
            Pair(testGenres, persistentSetOf()),
        )
}
