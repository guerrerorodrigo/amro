package com.rodrigoguerrero.ui.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox

internal class MovieItemSnapshotTest {

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun MovieItemLightTest() {
        TestComposable(isDarkMode = false)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun MovieItemDarkTest() {
        TestComposable(isDarkMode = true)
    }

    @Composable
    private fun TestComposable(isDarkMode: Boolean) {
        PreviewBox(isDarkTheme = isDarkMode) {
            MovieItem(data = testTrendingMovie)
        }
    }
}
