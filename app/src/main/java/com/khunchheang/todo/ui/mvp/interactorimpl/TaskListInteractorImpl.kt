package com.khunchheang.todo.ui.mvp.interactorimpl

import com.khunchheang.todo.data.local.dao.TaskDao
import com.khunchheang.todo.ui.base.basemvp.response.ResponseModel
import com.khunchheang.todo.ui.base.basemvp.response.SuccessResponseModel
import com.khunchheang.todo.ui.mvp.GetTaskList
import java.util.*

class TaskListInteractorImpl(private val currentDate: Date, private val taskDao: TaskDao) :
    GetTaskList.TaskListInteractor {

    override fun onGetTaskToday(completion: (responseModel: ResponseModel) -> Unit) {
        val lst = taskDao.loadAllTasksToday(currentDate.time)
        completion(SuccessResponseModel(lst))
    }

    override fun onGetTaskNextSevenDay(completion: (responseModel: ResponseModel) -> Unit) {
        val lst = taskDao.loadAllTasksNextSevenDay(currentDate.time)
        completion(SuccessResponseModel(lst))
    }

    override fun onGetTaskPriority(completion: (responseModel: ResponseModel) -> Unit) {
        val lst = taskDao.loadAllPriorityTasks()
        completion(SuccessResponseModel(lst))
    }

    override fun onGetTaskComplete(completion: (responseModel: ResponseModel) -> Unit) {
        val lst = taskDao.loadAllCompletedTasks()
        completion(SuccessResponseModel(lst))
    }

    override fun onGetTaskOverdue(completion: (responseModel: ResponseModel) -> Unit) {
        val lst = taskDao.loadAllTasksOverdue(currentDate.time)
        completion(SuccessResponseModel(lst))
    }

    override fun onCancelLoading() {

    }
}