package com.khunchheang.todo.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.khunchheang.todo.data.model.TodoModel

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo:TodoModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(options: List<TodoModel>)

    @Query("SELECT * FROM todos")
    abstract fun loadAll(): List<TodoModel>

    @Query("SELECT * FROM todos")
    fun loadAllTodo(): List<TodoModel>

}