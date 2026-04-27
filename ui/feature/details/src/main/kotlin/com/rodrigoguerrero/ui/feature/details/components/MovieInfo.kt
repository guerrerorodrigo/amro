package com.rodrigoguerrero.ui.feature.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews
import com.rodrigoguerrero.ui.feature.details.R
import com.rodrigoguerrero.ui.feature.details.models.MovieDetails

@Composable
internal fun MovieInfo(
    data: MovieDetails,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.padding(horizontal = AmroTheme.dimens.padding.md),
        verticalArrangement = Arrangement.spacedBy(AmroTheme.dimens.padding.xxs),
    ) {
        Text(
            text = data.title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        if (data.tagline.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.tagline, data.tagline),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontStyle = FontStyle.Italic,
                ),
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        MovieInfoRow(
            modifier = Modifier.fillMaxWidth(),
            voteAverage = data.voteAverage,
            runtime = data.runtime,
            releaseDate = data.releaseDate,
        )
        GenresStrip(genres = data.genres, modifier = Modifier.fillMaxWidth())
        Button(
            shape = RoundedCornerShape(size = AmroTheme.dimens.radius.xs),
            onClick = { context.openUrl(url = data.imdbUrl) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.imdb))
        }
    }
}

@WidgetPreviews
@Composable
private fun PreviewMovieInfo(
    @PreviewParameter(MovieDetailsParamProvider::class) data: MovieDetails,
) {
    PreviewBox {
        MovieInfo(data = data)
    }
}
