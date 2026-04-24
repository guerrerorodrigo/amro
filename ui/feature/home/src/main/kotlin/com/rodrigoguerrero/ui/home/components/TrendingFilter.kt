package com.rodrigoguerrero.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews
import com.rodrigoguerrero.ui.home.models.Genre
import com.rodrigoguerrero.ui.home.mvi.HomeAction
import com.rodrigoguerrero.ui.home.mvi.HomeAction.OnGenreTapped
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

@Composable
internal fun TrendingFilter(
    genres: ImmutableList<Genre>,
    selectedGenres: ImmutableSet<Int>,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        contentPadding = PaddingValues(horizontal = AmroTheme.dimens.padding.md),
        horizontalArrangement = Arrangement.spacedBy(AmroTheme.dimens.padding.md),
    ) {
        items(genres, key = { it.id }) { genre ->
            FilterChip(
                selected = selectedGenres.contains(genre.id),
                onClick = { onAction(OnGenreTapped(genre.id)) },
                label = {
                    Text(text = genre.name)
                },
            )
        }
    }
}

@WidgetPreviews
@Composable
private fun PreviewTrendingFilter() {
    val actionGenre = Genre(id = 1, name = "Action")
    val comedyGenre = Genre(id = 2, name = "Comedy")
    PreviewBox {
        TrendingFilter(
            genres = persistentListOf(actionGenre, comedyGenre),
            selectedGenres = persistentSetOf(1),
            onAction = {},
        )
    }
}
