package com.rodrigoguerrero.ui.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.ui.home.models.SortingType

internal class TrendingFilterDropDownSnapshotTest {

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun TrendingFilterDropDownLightTest() {
        TestComposable(isDarkMode = false)
    }

    @PreviewTest
    @Preview(showBackground = true)
    @Composable
    fun TrendingFilterDropDownDarkTest() {
        TestComposable(isDarkMode = true)
    }

    @Composable
    private fun TestComposable(isDarkMode: Boolean) {
        PreviewBox(isDarkTheme = isDarkMode) {
            TrendingFilterDropDownMenu(
                sortingType = SortingType.Popularity,
                onAction = {},
            )
        }
    }
}
