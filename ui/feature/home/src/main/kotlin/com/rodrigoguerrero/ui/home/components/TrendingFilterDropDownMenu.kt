package com.rodrigoguerrero.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.theme.AmroTheme
import com.rodrigoguerrero.theme.components.preview.PreviewBox
import com.rodrigoguerrero.theme.components.preview.WidgetPreviews
import com.rodrigoguerrero.ui.home.R
import com.rodrigoguerrero.ui.home.models.SortingType
import com.rodrigoguerrero.ui.home.mvi.HomeAction

@Composable
internal fun TrendingFilterDropDownMenu(
    sortingType: SortingType,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    Box {
        Row(
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(size = AmroTheme.dimens.radius.xs),
                )
                .clickable { isMenuExpanded = !isMenuExpanded }
                .padding(
                    vertical = AmroTheme.dimens.padding.xs,
                    horizontal = AmroTheme.dimens.padding.md,
                ),
        ) {
            Text(
                text = stringResource(R.string.sort_by),
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = stringResource(sortingType.label),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Icon(
                painter = painterResource(id = getDropdownIcon(isExpanded = isMenuExpanded)),
                contentDescription = stringResource(R.string.sort_by_dropdown_menu),
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false },
        ) {
            SortingType.entries.forEach { type ->
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(type.label))
                    },
                    onClick = {
                        onAction(HomeAction.OnSortTypeChanged(type))
                        isMenuExpanded = false
                    },
                )
            }
        }
    }
}

private fun getDropdownIcon(isExpanded: Boolean) = if (isExpanded) {
    R.drawable.ic_arrow_drop_up
} else {
    R.drawable.ic_arrow_drop_down
}

@WidgetPreviews
@Composable
private fun PreviewTrendingFilterDropDownMenu() {
    PreviewBox {
        TrendingFilterDropDownMenu(
            sortingType = SortingType.Title,
            onAction = {},
        )
    }
}