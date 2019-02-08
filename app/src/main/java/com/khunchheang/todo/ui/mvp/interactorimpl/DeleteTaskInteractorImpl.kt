package com.khunchheang.todo.ui.mvp.interactorimpl

import com.khunchheang.todo.data.local.dao.TaskDao
import com.khunchheang.todo.ui.base.basemvp.response.ResponseModel
import com.khunchheang.todo.ui.base.basemvp.response.SuccessResponseModel
import com.khunchheang.todo.ui.mvp.DeleteTask

class DeleteTaskInteractorImpl(private val taskDao: TaskDao) : DeleteTask.DeleteTaskInteractor {

    override fun onDeleteTask(taskId: Long, completion: (responseModel: ResponseModel) -> Unit) {
        taskDao.deleteTask(taskId)
        completion(SuccessResponseModel("Deleted task successfully"))
    }


    override fun onCancelLoading() {

    }

}