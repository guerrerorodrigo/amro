package com.rodrigoguerrero.ui.home.components

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.ScreenshotTestPreviews
import com.rodrigoguerrero.ui.home.models.SortingOrder
import com.rodrigoguerrero.ui.home.models.SortingType
import kotlinx.collections.immutable.persistentSetOf

internal class TrendingTopBarScreenshotTest {

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun TrendingTopBarDescendingLightTest() {
        TestComposable(sortingOrder = SortingOrder.Descending)
    }

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun TrendingTopBarAscendingLightTest() {
        TestComposable(sortingOrder = SortingOrder.Ascending)
    }

    @Composable
    private fun TestComposable(
        sortingOrder: SortingOrder,
    ) {
        PreviewBox {
            TrendingTopBar(
                genres = testGenres,
                sortingOrder = sortingOrder,
                sortingType = SortingType.Popularity,
                selectedGenres = persistentSetOf(),
                onAction = {},
            )
        }
    }
}
