package com.rodrigoguerrero.ui.feature.details.components

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.theme.ThemeDimensions

internal val ThemeDimensions.details: DetailsDimens
    get() = DetailsDimens(
        posterHeight = 480.dp
    )

internal data class DetailsDimens(
    val posterHeight: Dp,
)