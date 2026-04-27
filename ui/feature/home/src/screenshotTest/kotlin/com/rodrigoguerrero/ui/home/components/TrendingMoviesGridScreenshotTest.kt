package com.rodrigoguerrero.ui.home.components

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.ScreenshotTestPreviews
import com.rodrigoguerrero.ui.home.models.SortingOrder
import com.rodrigoguerrero.ui.home.models.SortingType

internal class TrendingMoviesGridScreenshotTest {

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun TrendingMoviesGridLightTest() {
        PreviewBox() {
            TrendingMoviesGrid(
                sortingType = SortingType.Popularity,
                sortingOrder = SortingOrder.Descending,
                trendingMovies = testTrendingMovies,
                onMovieClick = {},
            )
        }
    }
}
