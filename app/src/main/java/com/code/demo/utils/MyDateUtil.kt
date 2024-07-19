package com.code.demo.utils

import java.util.Calendar
import java.util.Date


fun getDayOfWeek(date: Date):String {

    val calendar = Calendar.getInstance()
    calendar.time=date
    val dayOfWeek =calendar.get(Calendar.DAY_OF_WEEK)
    when (dayOfWeek) {
        1->{return "Lun"}
        2->{return "Mar"}
        3->{return "Mer"}
        4->{return "Jeu"}
        5->{return "Ven"}
        6->{return "Sam"}
        7->{return "Dim"}
        else -> return ""
    }
}