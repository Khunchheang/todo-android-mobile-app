package com.khunchheang.todo.data.local.dao

import android.arch.persistence.room.*
import com.khunchheang.todo.data.model.db.TaskModel

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: TaskModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(options: List<TaskModel>)

    @Query("SELECT * FROM tb_tasks")
    fun loadAll(): List<TaskModel>

    @Query("SELECT * FROM tb_tasks WHERE due_date = :todayTime AND is_complete = 0")
    fun loadAllTasksToday(todayTime: Long): List<TaskModel>

    @Query("SELECT * FROM tb_tasks WHERE due_date > :currentTime AND is_complete =0")
    fun loadAllTasksNextSevenDay(currentTime: Long): List<TaskModel>

    @Query("SELECT * FROM tb_tasks WHERE is_complete = 1")
    fun loadAllCompletedTasks(): List<TaskModel>

    @Query("SELECT * FROM tb_tasks WHERE is_priority = 1 AND is_complete = 0")
    fun loadAllPriorityTasks(): List<TaskModel>

    @Query("SELECT * FROM tb_tasks WHERE task_id = :id")
    fun getTaskById(id: Long): TaskModel?

    @Update
    fun updateTask(todo: TaskModel)

    @Query("DELETE FROM tb_tasks WHERE task_id = :id")
    fun deleteTask(id: Long)

}