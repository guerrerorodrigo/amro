package com.rodrigoguerrero.ui.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import kotlinx.collections.immutable.persistentSetOf

internal class TrendingFilterScreenshotTest {

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun TrendingFilterLightTest() {
        TestComposable(isDarkMode = false)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun TrendingFilterDarkTest() {
        TestComposable(isDarkMode = true)
    }

    @Composable
    private fun TestComposable(isDarkMode: Boolean) {
        PreviewBox(isDarkTheme = isDarkMode) {
            TrendingFilter(
                genres = testGenres,
                selectedGenres = persistentSetOf(1),
                onAction = {},
            )
        }
    }
}
