package io.lostImagin4tion.financialHelper.data.room.converters

import android.icu.text.SimpleDateFormat
import android.icu.util.GregorianCalendar
import java.util.Locale

object DateConverter {

    private val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    private val calendar = GregorianCalendar()

    fun toDateInMillis(value: String): Long {
        return formatter.parse(value).time
    }

    fun fromDateInMillis(value: Long): String {
        calendar.timeInMillis = value
        return formatter.format(calendar.timeInMillis)
    }
}
