package com.rodrigoguerrero.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.R
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews

@Composable
internal fun MovieImage(
    imageUrl: String,
    title: String,
    modifier: Modifier = Modifier,
) {
    if (LocalInspectionMode.current) {
        Image(
            painter = painterResource(R.drawable.movie_placeholder),
            contentDescription = null
        )
    } else {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
            placeholder = painterResource(id = R.drawable.movie_placeholder),
            error = painterResource(id = R.drawable.movie_placeholder),
            contentScale = ContentScale.FillBounds,
            contentDescription = title,
            modifier = modifier
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(AmroTheme.dimens.radius.xxs)),
        )
    }
}


@WidgetPreviews
@Composable
private fun PreviewMovieItem() {
    PreviewBox {
        MovieImage(title = "Title", imageUrl = "")
    }
}
