package com.rodrigoguerrero.ui.feature.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews
import com.rodrigoguerrero.ui.feature.details.R
import com.rodrigoguerrero.ui.feature.details.models.MovieDetails
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun MovieDetails(
    data: MovieDetails,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(AmroTheme.dimens.padding.xs),
        ) {
            MoviePoster(url = data.posterUrl)
            MovieInfo(data = data)

            DetailsInfoSection(
                title = stringResource(R.string.overview),
                content = data.overview,
                modifier = Modifier.padding(horizontal = AmroTheme.dimens.padding.md),
            )
            DetailsInfoSection(
                title = stringResource(R.string.status_of_this_movie),
                content = data.status,
                modifier = Modifier.padding(horizontal = AmroTheme.dimens.padding.md),
            )
            TwoDetailSections(
                firstTitle = stringResource(R.string.budget),
                firstContent = data.budget,
                secondTitle = stringResource(R.string.revenue),
                secondContent = data.revenue,
            )
            TwoDetailSections(
                firstTitle = stringResource(R.string.number_of_votes),
                firstContent = data.voteCount.toString(),
                secondTitle = stringResource(R.string.rating),
                secondContent = data.voteAverage.toString(),
            )
        }
        IconButton(
            modifier = Modifier.padding(all = AmroTheme.dimens.padding.md),
            onClick = onBack,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            shape = RoundedCornerShape(size = AmroTheme.dimens.padding.xs),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = stringResource(R.string.back),
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
private fun TwoDetailSections(
    firstTitle: String,
    firstContent: String,
    secondTitle: String,
    secondContent: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AmroTheme.dimens.padding.md),
        horizontalArrangement = Arrangement.spacedBy(AmroTheme.dimens.padding.xs),
    ) {
        DetailsInfoSection(
            title = firstTitle,
            content = firstContent,
            modifier = Modifier.weight(1f),
        )
        DetailsInfoSection(
            title = secondTitle,
            content = secondContent,
            modifier = Modifier.weight(1f),
        )
    }
}

@WidgetPreviews
@Composable
private fun PreviewMovieDetails() {
    val data = MovieDetails(
        title = "title",
        tagline = "tagline",
        overview = "overview",
        budget = "$5000000",
        imdbUrl = "",
        posterUrl = "",
        voteAverage = 8.4,
        revenue = "$33330000",
        runtime = 98,
        genres = persistentListOf("Animation", "Comedy", "Adventure", "Fantasy"),
        voteCount = 12345,
        status = "released",
        releaseDate = "May 31, 2017",
    )
    PreviewBox {
        MovieDetails(data = data, onBack = {})
    }
}
