package com.rodrigoguerrero.ui.feature.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.R as ThemeR

@Composable
internal fun MoviePoster(
    url: String,
    modifier: Modifier = Modifier,
) {
    if (LocalInspectionMode.current) {
        Image(
            painter = painterResource(ThemeR.drawable.movie_placeholder),
            contentDescription = null,
            modifier = modifier
                .height(AmroTheme.dimens.details.posterHeight)
                .fillMaxWidth(),
        )
    } else {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(url)
                .build(),
            placeholder = painterResource(ThemeR.drawable.movie_placeholder),
            error = painterResource(ThemeR.drawable.movie_placeholder),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(AmroTheme.dimens.details.posterHeight)
                .fillMaxWidth(),
        )
    }
}
