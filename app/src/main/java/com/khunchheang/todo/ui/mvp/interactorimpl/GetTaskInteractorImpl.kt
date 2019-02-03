package com.khunchheang.todo.ui.mvp.interactorimpl

import com.khunchheang.todo.data.local.dao.TaskDao
import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.base.basemvp.baseinteractorimpl.BaseInteractorImpl
import com.khunchheang.todo.ui.mvp.GetTask

class GetTaskInteractorImpl(private val taskDao: TaskDao) : BaseInteractorImpl<TaskModel>(), GetTask.GetTaskInteractor {
    override fun onCancelLoading() {
    }

    override fun onGetTask(taskId: Long) {
        val task = taskDao.getTaskById(taskId)
        if (task == null) {
            responseListener?.onException("Cannot get current task")
            return
        }
        responseListener?.onSuccess(task)
    }
}