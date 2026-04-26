package com.rodrigoguerrero.domain.common

import java.text.NumberFormat
import javax.inject.Inject

/**
 * Formats numbers as currency
 */
class CurrencyFormatter @Inject constructor() {

    /**
     * Formats the provided value to a currency string
     */
    fun format(value: Int): String {
        val formatter = NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 0
        }
        val formatted = formatter.format(value)
        return formatted
    }
}
