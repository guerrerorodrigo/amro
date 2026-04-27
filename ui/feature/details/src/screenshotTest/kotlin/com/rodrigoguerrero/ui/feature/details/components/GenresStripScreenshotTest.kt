package com.rodrigoguerrero.ui.feature.details.components

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.ScreenshotTestPreviews

internal class GenresStripScreenshotTest {

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun GenresStripTest() {
        PreviewBox {
            GenresStrip(genres = testGenres)
        }
    }
}
