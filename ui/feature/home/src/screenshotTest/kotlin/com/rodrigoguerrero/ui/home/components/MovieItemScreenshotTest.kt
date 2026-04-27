package com.rodrigoguerrero.ui.home.components

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.ScreenshotTestPreviews

internal class MovieItemScreenshotTest {

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun MovieItemLightTest() {
        PreviewBox {
            MovieItem(data = testTrendingMovie, onClick = {})
        }
    }
}
