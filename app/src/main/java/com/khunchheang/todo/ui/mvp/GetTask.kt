package com.khunchheang.todo.ui.mvp

import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.BaseMvpView
import com.khunchheang.todo.ui.base.basemvp.BasePresenter
import com.khunchheang.todo.ui.base.basemvp.response.ResponseModel

interface GetTask {

    interface GetTaskInteractor : BaseInteractor<TaskModel> {
        fun onGetTask(taskId: Long, completion: (responseModel: ResponseModel) -> Unit)
    }

    interface GetTaskPresenter : BasePresenter<GetTaskView> {
        fun getTaskById(taskId: Long)
    }

    interface GetTaskView : BaseMvpView {
        fun onTaskResponse(taskModel: TaskModel)
    }

}