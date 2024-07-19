package com.code.demo.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale


fun Date.getDayFromDate(): Int {
    val calendar = GregorianCalendar()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun Date.convertDateStringFormat(format: String = "dd MMM yyyy"): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(this)
}





