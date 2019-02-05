package com.khunchheang.todo.ui.mvp.interactorimpl

import com.khunchheang.todo.data.local.dao.TaskDao
import com.khunchheang.todo.data.model.db.ListTaskModel
import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.base.basemvp.baseinteractorimpl.BaseInteractorImpl
import com.khunchheang.todo.ui.mvp.GetTaskList
import java.util.*

class TaskListInteractorImpl(private val currentDate: Date, private val taskDao: TaskDao) :
    BaseInteractorImpl<ListTaskModel>(), GetTaskList.TaskListInteractor {

    override fun onCancelLoading() {
    }

    override fun onGetTaskToday() {
        val lst = taskDao.loadAllTasksToday(currentDate.time)
        onGetListTasks(lst)
    }

    override fun onGetTaskNextSevenDay() {
        val lst = taskDao.loadAllTasksNextSevenDay(currentDate.time)
        onGetListTasks(lst)
    }

    override fun onGetTaskPriority() {
        val lst = taskDao.loadAllPriorityTasks()
        onGetListTasks(lst)
    }

    override fun onGetTaskComplete() {
        val lst = taskDao.loadAllCompletedTasks()
        onGetListTasks(lst)
    }

    override fun onGetTaskOverdue() {
        val lst = taskDao.loadAllTasksOverdue(currentDate.time)
        onGetListTasks(lst)
    }

    private fun onGetListTasks(lstTasks: List<TaskModel>) {
        val listTaskModel = ListTaskModel(lstTasks)
        responseListener?.onSuccess(listTaskModel)
    }
}