package com.rodrigoguerrero.ui.home.components

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.ScreenshotTestPreviews
import com.rodrigoguerrero.ui.home.models.SortingType

internal class TrendingFilterDropDownScreenshotTest {

    @PreviewTest
    @ScreenshotTestPreviews
    @Composable
    fun TrendingFilterDropDownLightTest() {
        PreviewBox() {
            TrendingFilterDropDownMenu(
                sortingType = SortingType.Popularity,
                onAction = {},
            )
        }
    }
}
