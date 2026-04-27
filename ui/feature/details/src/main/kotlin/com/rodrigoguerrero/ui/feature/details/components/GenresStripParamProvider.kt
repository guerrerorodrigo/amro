package com.rodrigoguerrero.ui.feature.details.components

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal val testGenres = persistentListOf("Comedy", "Action", "Adventure", "Family", "Fantasy", "Animation")

internal class MovieItemParamProvider : PreviewParameterProvider<ImmutableList<String>> {
    override val values: Sequence<ImmutableList<String>>
        get() = sequenceOf(testGenres)
}
