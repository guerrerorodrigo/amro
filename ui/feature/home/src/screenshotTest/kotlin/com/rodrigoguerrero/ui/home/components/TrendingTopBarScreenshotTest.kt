package com.rodrigoguerrero.ui.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.ui.home.models.SortingOrder
import com.rodrigoguerrero.ui.home.models.SortingType
import kotlinx.collections.immutable.persistentSetOf

internal class TrendingTopBarScreenshotTest {

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun TrendingTopBarDescendingLightTest() {
        TestComposable(isDarkMode = false, sortingOrder = SortingOrder.Descending)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun TrendingTopBarDescendingDarkTest() {
        TestComposable(isDarkMode = true, sortingOrder = SortingOrder.Descending)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun TrendingTopBarAscendingLightTest() {
        TestComposable(isDarkMode = false, sortingOrder = SortingOrder.Ascending)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun TrendingTopBarAscendingDarkTest() {
        TestComposable(isDarkMode = true, sortingOrder = SortingOrder.Ascending)
    }

    @Composable
    private fun TestComposable(
        isDarkMode: Boolean,
        sortingOrder: SortingOrder,
    ) {
        PreviewBox(isDarkTheme = isDarkMode) {
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
