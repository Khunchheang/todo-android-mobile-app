package com.khunchheang.todo.data.model.db

import android.arch.persistence.room.*
import com.khunchheang.todo.data.local.converter.DateConverter
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(tableName = "tb_tasks")
class TaskModel {
    @PrimaryKey
    @ColumnInfo(name = "task_id")
    var id: Long? = null

    @ColumnInfo(name = "task_name")
    var task: String? = null

    @ColumnInfo(name = "due_date")
    @TypeConverters(DateConverter::class)
    var dueDate: Date? = null

    @ColumnInfo(name = "is_priority")
    var isPriority: Boolean? = null

    @ColumnInfo(name = "is_complete")
    var isComplete: Boolean? = null
}