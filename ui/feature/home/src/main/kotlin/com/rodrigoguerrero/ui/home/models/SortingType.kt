package com.rodrigoguerrero.ui.home.models

import androidx.annotation.StringRes
import com.rodrigoguerrero.ui.home.R

internal enum class SortingType(@StringRes val label: Int) {
    Popularity(R.string.popularity),
    Title(R.string.title),
    ReleaseDate(R.string.release_date),
    ;

    fun comparator(): Comparator<TrendingMovie> = when (this) {
        Popularity -> compareBy { it.popularity }
        Title -> compareBy { it.title }
        ReleaseDate -> compareBy { it.releaseDate }
    }
}
