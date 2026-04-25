package com.rodrigoguerrero.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews
import com.rodrigoguerrero.ui.home.R
import com.rodrigoguerrero.ui.home.models.Genre
import com.rodrigoguerrero.ui.home.models.SortingOrder
import com.rodrigoguerrero.ui.home.models.SortingType
import com.rodrigoguerrero.ui.home.mvi.HomeAction
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

@Composable
internal fun TrendingTopBar(
    genres: ImmutableList<Genre>,
    selectedGenres: ImmutableSet<Int>,
    sortingOrder: SortingOrder,
    sortingType: SortingType,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding(),
    ) {
        TrendingFilter(
            genres = genres,
            selectedGenres = selectedGenres,
            onAction = onAction,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AmroTheme.dimens.padding.md),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TrendingFilterDropDownMenu(
                sortingType = sortingType,
                onAction = onAction,
            )

            IconButton(
                onClick = { onAction(HomeAction.OnSortOrderTapped) },
            ) {
                Icon(
                    modifier = Modifier.rotate(sortingOrder.getRotationDegrees()),
                    painter = painterResource(id = R.drawable.ic_sort),
                    contentDescription = stringResource(R.string.sorting_order),
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

private fun SortingOrder.getRotationDegrees() = when (this) {
    SortingOrder.Ascending -> 180f
    SortingOrder.Descending -> 0f
}

@WidgetPreviews
@Composable
private fun PreviewTrendingTopBar() {
    val actionGenre = Genre(id = 1, name = "Action")
    val comedyGenre = Genre(id = 2, name = "Comedy")
    PreviewBox {
        TrendingTopBar(
            genres = persistentListOf(actionGenre, comedyGenre),
            selectedGenres = persistentSetOf(1),
            sortingOrder = SortingOrder.Descending,
            onAction = {},
            sortingType = SortingType.Popularity,
        )
    }
}
