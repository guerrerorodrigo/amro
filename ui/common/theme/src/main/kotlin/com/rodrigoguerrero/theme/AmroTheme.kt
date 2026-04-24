package com.rodrigoguerrero.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

/**
 * Object that includes different theme values
 */
object AmroTheme {
    /**
     * Contains the different [ThemeDimensions] available for the theme
     */
    val dimens: ThemeDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current
}
