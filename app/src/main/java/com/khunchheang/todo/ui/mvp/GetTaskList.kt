package com.khunchheang.todo.ui.mvp

import com.khunchheang.todo.data.model.db.ListTaskModel
import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.BaseMvpView
import com.khunchheang.todo.ui.base.basemvp.BasePresenter
import com.khunchheang.todo.util.TaskType

interface GetTaskList {

    interface TaskListInteractor : BaseInteractor<ListTaskModel> {
        fun onGetTaskToday()

        fun onGetTaskNextSevenDay()

        fun onGetTaskPriority()

        fun onGetTaskComplete()

        fun onGetTaskOverdue()
    }

    interface TaskListPresenter : BasePresenter<TaskListView> {
        fun getTaskList(taskType: TaskType)
    }

    interface TaskListView : BaseMvpView {
        fun onTaskResponse(lstOptionsSet: List<TaskModel>)
    }

}