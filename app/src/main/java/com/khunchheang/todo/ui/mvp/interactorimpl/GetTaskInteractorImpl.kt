package com.khunchheang.todo.ui.mvp.interactorimpl

import com.khunchheang.todo.data.local.dao.TaskDao
import com.khunchheang.todo.ui.base.basemvp.response.ExceptionResponseModel
import com.khunchheang.todo.ui.base.basemvp.response.ResponseModel
import com.khunchheang.todo.ui.base.basemvp.response.SuccessResponseModel
import com.khunchheang.todo.ui.mvp.GetTask

class GetTaskInteractorImpl(private val taskDao: TaskDao) : GetTask.GetTaskInteractor {

    override fun onGetTask(taskId: Long, completion: (responseModel: ResponseModel) -> Unit) {
        val task = taskDao.getTaskById(taskId)
        if (task == null) {
            completion(ExceptionResponseModel("Cannot get current task"))
            return
        }
        completion(SuccessResponseModel(task))
    }

    override fun onCancelLoading() {

    }

}