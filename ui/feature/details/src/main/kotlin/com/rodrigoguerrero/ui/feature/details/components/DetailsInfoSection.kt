package com.rodrigoguerrero.ui.feature.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews

@Composable
internal fun DetailsInfoSection(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(size = AmroTheme.dimens.padding.xs),
            )
            .padding(all = AmroTheme.dimens.padding.md)
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
        )
        Text(
            text = content,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@WidgetPreviews
@Composable
private fun PreviewDetailsInfoSection() {
    PreviewBox {
        DetailsInfoSection(
            title = "Section title",
            content = "This is the section content",
        )
    }
}
