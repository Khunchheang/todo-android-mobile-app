package com.khunchheang.todo.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.khunchheang.todo.data.local.dao.TaskDao
import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.util.AppConstants

@Database(
    entities = [
        TaskModel::class
    ],
    version = AppConstants.DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TaskDao

}