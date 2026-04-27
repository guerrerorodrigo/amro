package com.rodrigoguerrero.ui.home.components

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.ScreenshotTestPreviews
import kotlinx.collections.immutable.persistentSetOf

internal class TrendingFilterScreenshotTest {

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun TrendingFilterLightTest() {
        PreviewBox {
            TrendingFilter(
                genres = testGenres,
                selectedGenres = persistentSetOf(1),
                onAction = {},
            )
        }
    }
}
