package com.rodrigoguerrero.theme.components.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.AppTheme

@Composable
fun PreviewBox(
    modifier: Modifier = Modifier,
    padding: Dp = AmroTheme.dimens.padding.md,
    content: @Composable () -> Unit,
) {
    AppTheme(
        darkTheme = isSystemInDarkTheme(),
    ) {
        Box(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(all = padding),
        ) {
            content()
        }
    }
}
