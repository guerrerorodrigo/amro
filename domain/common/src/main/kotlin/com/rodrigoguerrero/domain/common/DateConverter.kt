package com.rodrigoguerrero.domain.common

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import javax.inject.Inject

/**
 * Utility class for date time operations
 */
class DateConverter @Inject constructor() {

    /**
     * Converts a date with the format 2026-03-15 to epoch milliseconds in the currentSystem default [TimeZone]
     * @param date the date to convert
     * @return the date converted to epoch milliseconds or null if the provided date is invalid
     */
    fun toEpochMilliseconds(date: String): Long? = try {
        val localDate = LocalDate.parse(date)
        val instant = localDate.atStartOfDayIn(timeZone = TimeZone.currentSystemDefault())
        instant.toEpochMilliseconds()
    } catch (_: Exception) {
        null
    }

    /**
     * Converts a date with the format 2026-03-15 to a readable date of month day, year
     * @param date the date to convert
     * @return the date converted to the format month day, year
     */
    fun toReadableDate(date: String): String? = try {
        val localDate = LocalDate.parse(date)
        val customFormat = LocalDate.Format {
            day()
            char(' ')
            monthName(MonthNames.ENGLISH_ABBREVIATED)
            chars(", ")
            year()
        }
        localDate.format(customFormat)
    } catch(_: Exception) {
        null
    }
}
