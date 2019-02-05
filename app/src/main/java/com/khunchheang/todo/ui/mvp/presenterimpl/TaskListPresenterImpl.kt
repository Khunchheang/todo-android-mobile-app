package com.khunchheang.todo.ui.mvp.presenterimpl

import android.os.Handler
import com.khunchheang.todo.data.model.db.ListTaskModel
import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.basepresenterimpl.BasePresenterImpl
import com.khunchheang.todo.ui.mvp.GetTaskList
import com.khunchheang.todo.util.TaskType

class TaskListPresenterImpl(private val taskListInter: GetTaskList.TaskListInteractor) :
    BasePresenterImpl<ListTaskModel, GetTaskList.TaskListView>(), GetTaskList.TaskListPresenter {

    override fun onSuccess(data: ListTaskModel) {
        view?.hideLoading()
        view?.onTaskResponse(data.lstTaskModel)
    }

    override fun getTaskList(taskType: TaskType) {
        view?.showLoading()
        Handler().postDelayed({
            when (taskType) {
                TaskType.Today -> taskListInter.onGetTaskToday()

                TaskType.NextSevenDay -> taskListInter.onGetTaskNextSevenDay()

                TaskType.Priority -> taskListInter.onGetTaskPriority()

                TaskType.Complete -> taskListInter.onGetTaskComplete()

                TaskType.Overdue -> taskListInter.onGetTaskOverdue()
            }
        }, 300)
    }

    override fun getInter(): BaseInteractor<ListTaskModel> {
        return taskListInter
    }
}