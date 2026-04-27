package com.rodrigoguerrero.ui.feature.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews
import com.rodrigoguerrero.ui.feature.details.R
import com.rodrigoguerrero.ui.feature.details.models.MovieDetails

@Composable
internal fun MovieInfoRow(
    voteAverage: Double,
    runtime: Int,
    releaseDate: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = stringResource(R.string.rating),
            modifier = Modifier.scale(0.6F),
            tint = MaterialTheme.colorScheme.secondary,
        )
        Text(
            text = stringResource(R.string.movie_info, voteAverage, runtime, releaseDate,),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )
    }
}

@WidgetPreviews
@Composable
private fun PreviewMovieInfoRow(
    @PreviewParameter(MovieDetailsParamProvider::class) data: MovieDetails,
) {
    PreviewBox {
        MovieInfoRow(
            voteAverage = data.voteAverage,
            runtime = data.runtime,
            releaseDate = data.releaseDate,
        )
    }
}
