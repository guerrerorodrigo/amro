package com.rodrigoguerrero.ui.home.components

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rodrigoguerrero.ui.home.models.Genre
import com.rodrigoguerrero.ui.home.models.SortingOrder
import kotlinx.collections.immutable.ImmutableList

internal val previewTrendingTopBar = sequenceOf(
    Pair(testGenres, SortingOrder.Descending),
    Pair(testGenres, SortingOrder.Ascending),
)

internal class TrendingTopBarParamProvider : PreviewParameterProvider<Pair<ImmutableList<Genre>, SortingOrder>> {
    override val values: Sequence<Pair<ImmutableList<Genre>, SortingOrder>>
        get() = previewTrendingTopBar
}
