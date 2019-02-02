package com.khunchheang.todo.data.local.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return try {
            if (value == null) null
            else Date(value)
        } catch (e: Exception) {
            e.localizedMessage
            null
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
