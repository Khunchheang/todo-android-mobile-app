package com.khunchheang.todo.ui.mvp

import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.BaseMvpView
import com.khunchheang.todo.ui.base.basemvp.BasePresenter
import com.khunchheang.todo.ui.base.basemvp.response.ResponseModel
import com.khunchheang.todo.util.TaskType

interface GetTaskList {

    interface TaskListInteractor : BaseInteractor<List<TaskModel>> {
        fun onGetTaskToday(completion: (responseModel: ResponseModel) -> Unit)

        fun onGetTaskNextSevenDay(completion: (responseModel: ResponseModel) -> Unit)

        fun onGetTaskPriority(completion: (responseModel: ResponseModel) -> Unit)

        fun onGetTaskComplete(completion: (responseModel: ResponseModel) -> Unit)

        fun onGetTaskOverdue(completion: (responseModel: ResponseModel) -> Unit)
    }

    interface TaskListPresenter : BasePresenter<TaskListView> {
        fun getTaskList(taskType: TaskType)
    }

    interface TaskListView : BaseMvpView {
        fun onTaskResponse(lstOptionsSet: List<TaskModel>)
    }

}