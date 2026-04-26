package com.rodrigoguerrero.ui.feature.details.components

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

internal fun Context.openUrl(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
}
