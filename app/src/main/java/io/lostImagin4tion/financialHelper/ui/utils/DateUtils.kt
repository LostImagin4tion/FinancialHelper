package io.lostImagin4tion.financialHelper.ui.utils

import java.util.Calendar

fun Calendar.getRuFormattedDate(): String {
    val year = this.get(Calendar.YEAR)
    val month = this.get(Calendar.MONTH) + 1
    val day = this.get(Calendar.DAY_OF_MONTH)

    val formattedYear = if (year < 10) "0$year" else year.toString()
    val formattedMonth = if (month < 10) "0$month" else month.toString()
    val formattedDay = if (day < 10) "0$day" else day.toString()

    return "$formattedDay.$formattedMonth.$formattedYear"
}
