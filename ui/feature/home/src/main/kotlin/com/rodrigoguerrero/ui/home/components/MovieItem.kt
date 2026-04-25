package com.rodrigoguerrero.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews
import com.rodrigoguerrero.ui.home.models.TrendingMovie

@Composable
internal fun MovieItem(
    data: TrendingMovie,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(AmroTheme.dimens.padding.xs),
    ) {
        MovieImage(
            imageUrl = data.imageUrl,
            title = data.title,
        )
        Column {
            Text(
                text = data.title,
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
            )
            data.genres?.let {
                Text(
                    text = data.genres,
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 1,
                )
            }
        }
    }
}

@WidgetPreviews
@Composable
private fun PreviewMovieItem(
    @PreviewParameter(MovieItemParamProvider::class) data: TrendingMovie,
) {
    PreviewBox {
        MovieItem(data = data)
    }
}
