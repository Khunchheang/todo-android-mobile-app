package com.khunchheang.todo.ui.mvp.presenterimpl

import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.basepresenterimpl.BasePresenterImpl
import com.khunchheang.todo.ui.mvp.GetTask

class GetTaskPresenterImpl(private val getTaskInter: GetTask.GetTaskInteractor) :
    BasePresenterImpl<TaskModel, GetTask.GetTaskView>(), GetTask.GetTaskPresenter {
    override fun getInter(): BaseInteractor<TaskModel> {
        return getTaskInter
    }

    override fun onSuccess(data: TaskModel) {
        view?.onTaskResponse(data)
    }

    override fun getTaskById(taskId: Long) {
        view?.showLoading()

        getTaskInter.onGetTask(taskId){
            onResponseData(it)
        }
    }
}