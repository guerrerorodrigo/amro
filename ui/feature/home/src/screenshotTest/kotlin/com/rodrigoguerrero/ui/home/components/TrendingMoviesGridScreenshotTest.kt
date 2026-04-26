package com.rodrigoguerrero.ui.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.ui.home.models.SortingOrder
import com.rodrigoguerrero.ui.home.models.SortingType

internal class TrendingMoviesGridScreenshotTest {

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun TrendingMoviesGridLightTest() {
        TestComposable(isDarkMode = false)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun TrendingMoviesGridDarkTest() {
        TestComposable(isDarkMode = true)
    }

    @Composable
    private fun TestComposable(isDarkMode: Boolean) {
        PreviewBox(isDarkTheme = isDarkMode) {
            TrendingMoviesGrid(
                sortingType = SortingType.Popularity,
                sortingOrder = SortingOrder.Descending,
                trendingMovies = testTrendingMovies,
                onMovieClick = {},
            )
        }
    }
}
