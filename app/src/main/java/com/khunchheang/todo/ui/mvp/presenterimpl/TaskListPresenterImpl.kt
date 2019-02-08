package com.khunchheang.todo.ui.mvp.presenterimpl

import android.os.Handler
import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.basepresenterimpl.BasePresenterImpl
import com.khunchheang.todo.ui.mvp.GetTaskList
import com.khunchheang.todo.util.TaskType

class TaskListPresenterImpl(private val taskListInter: GetTaskList.TaskListInteractor) :
    BasePresenterImpl<List<TaskModel>, GetTaskList.TaskListView>(), GetTaskList.TaskListPresenter {

    override fun onSuccess(data: List<TaskModel>) {
        view?.onTaskResponse(data)
    }

    override fun getTaskList(taskType: TaskType) {
        view?.showLoading()

        Handler().postDelayed({
            when (taskType) {
                TaskType.Today -> taskListInter.onGetTaskToday {
                    onResponseData(it)
                }

                TaskType.NextSevenDay -> taskListInter.onGetTaskNextSevenDay {
                    onResponseData(it)
                }

                TaskType.Priority -> taskListInter.onGetTaskPriority {
                    onResponseData(it)
                }

                TaskType.Complete -> taskListInter.onGetTaskComplete {
                    onResponseData(it)
                }

                TaskType.Overdue -> taskListInter.onGetTaskOverdue {
                    onResponseData(it)
                }
            }
        }, 300)
    }

    override fun getInter(): BaseInteractor<List<TaskModel>> {
        return taskListInter
    }
}