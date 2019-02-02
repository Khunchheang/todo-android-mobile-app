package com.khunchheang.todo.data.model

import android.arch.persistence.room.*
import com.khunchheang.todo.data.local.converter.DateConverter
import java.util.*

@Entity(tableName = "todos")
class TodoModel {
    @PrimaryKey
    var id: Long? = null

    @ColumnInfo(name = "todo_name")
    var name: String? = null

    @ColumnInfo(name = "created_at")
    @TypeConverters(DateConverter::class)
    var createdAt: Date? = null

    @ColumnInfo(name = "updated_at")
    @TypeConverters(DateConverter::class)
    var updatedAt: Date? = null
}