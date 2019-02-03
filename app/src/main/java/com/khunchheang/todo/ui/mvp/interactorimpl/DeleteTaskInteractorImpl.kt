package com.khunchheang.todo.ui.mvp.interactorimpl

import com.khunchheang.todo.data.local.dao.TaskDao
import com.khunchheang.todo.ui.base.basemvp.baseinteractorimpl.BaseInteractorImpl
import com.khunchheang.todo.ui.mvp.DeleteTask

class DeleteTaskInteractorImpl(private val taskDao: TaskDao) : BaseInteractorImpl<String>(),
    DeleteTask.DeleteTaskInteractor {
    override fun onCancelLoading() {
    }

    override fun onDelteTask(taskId: Long) {
        taskDao.deleteTask(taskId)
        responseListener?.onSuccess("Deleted task successfully")
    }
}