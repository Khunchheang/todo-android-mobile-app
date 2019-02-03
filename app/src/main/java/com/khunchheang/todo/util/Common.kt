package com.khunchheang.todo.util

import android.text.format.DateFormat
import java.util.*

object Common {

    fun getDisplayMonthAndDay(date: Date): String {
        val monthString = DateFormat.format("MMM", date) as String // Jun
        val day = DateFormat.format("dd", date) as String //06
        return "$monthString $day"
    }

}

