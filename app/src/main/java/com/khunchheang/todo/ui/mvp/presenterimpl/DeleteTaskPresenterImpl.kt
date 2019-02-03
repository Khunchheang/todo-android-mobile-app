package com.khunchheang.todo.ui.mvp.presenterimpl

import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.basepresenterimpl.BasePresenterImpl
import com.khunchheang.todo.ui.mvp.DeleteTask

class DeleteTaskPresenterImpl(private val deleteTaskInter: DeleteTask.DeleteTaskInteractor) :
    BasePresenterImpl<String, DeleteTask.DeleteTaskView>(), DeleteTask.DeleteTaskPresenter {
    override fun getInter(): BaseInteractor<String> {
        return deleteTaskInter
    }

    override fun onSuccess(data: String) {
        view?.hideLoading()
        view?.onDeleteTaskSuccess(data)
    }

    override fun deleteTask(taskId: Long) {
        view?.showLoading()
        deleteTaskInter.onDelteTask(taskId)
    }
}