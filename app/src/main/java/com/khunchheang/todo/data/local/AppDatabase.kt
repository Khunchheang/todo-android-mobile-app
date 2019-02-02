package com.khunchheang.todo.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.khunchheang.todo.data.local.dao.TodoDao
import com.khunchheang.todo.data.model.TodoModel
import com.khunchheang.todo.util.AppConstants

@Database(
    entities = [
        TodoModel::class
    ],
    version = AppConstants.DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

}