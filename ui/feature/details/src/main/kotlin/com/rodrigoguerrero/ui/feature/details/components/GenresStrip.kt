package com.rodrigoguerrero.ui.feature.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun GenresStrip(
    genres: ImmutableList<String>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(vertical = AmroTheme.dimens.padding.xxs),
    ) {
        genres.forEach { genre ->
            Box(
                modifier = Modifier
                    .padding(all = AmroTheme.dimens.padding.xxs)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = RoundedCornerShape(size = AmroTheme.dimens.radius.xs)
                    )
                    .padding(all = AmroTheme.dimens.padding.xs)
            ) {
                Text(
                    text = genre,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@WidgetPreviews
@Composable
private fun PreviewGenresStrip(
    @PreviewParameter(MovieItemParamProvider::class) genres: ImmutableList<String>,
) {
    PreviewBox {
        GenresStrip(genres = genres)
    }
}
