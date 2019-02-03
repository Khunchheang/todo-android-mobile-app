package com.khunchheang.todo.ui.mvp

import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.BaseMvpView
import com.khunchheang.todo.ui.base.basemvp.BasePresenter

interface DeleteTask {

    interface DeleteTaskInteractor : BaseInteractor<String> {
        fun onDelteTask(taskId: Long)
    }

    interface DeleteTaskPresenter : BasePresenter<DeleteTaskView> {
        fun deleteTask(taskId: Long)
    }

    interface DeleteTaskView : BaseMvpView {
        fun onDeleteTaskSuccess(msg: String)
    }

}