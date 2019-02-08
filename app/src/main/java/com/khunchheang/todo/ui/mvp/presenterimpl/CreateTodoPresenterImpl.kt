package com.khunchheang.todo.ui.mvp.presenterimpl

import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.basepresenterimpl.BasePresenterImpl
import com.khunchheang.todo.ui.base.basemvp.response.ErrorResponseModel
import com.khunchheang.todo.ui.base.basemvp.response.ExceptionResponseModel
import com.khunchheang.todo.ui.base.basemvp.response.SuccessResponseModel
import com.khunchheang.todo.ui.mvp.CreateTodo
import java.util.*

class CreateTodoPresenterImpl(private val createTodoInter: CreateTodo.CreateTodoInteractor) :
    BasePresenterImpl<TaskModel, CreateTodo.CreateTodoView>(), CreateTodo.CreateTodoPresenter {

    override fun onSuccess(data: TaskModel) {
        view?.onCreateTodoSuccess(data)
    }

    override fun getInter(): BaseInteractor<TaskModel> {
        return createTodoInter
    }

    override fun createTodo(taskId: Long, task: String, dueDate: Date, isPriority: Boolean, isComplete: Boolean) {
        if (task.isEmpty()) {
            view?.showError("Task required")
            return
        }

        view?.showLoading()

        val todoModel = TaskModel()
        if (taskId > 0) todoModel.id = taskId
        todoModel.task = task
        todoModel.dueDate = dueDate
        todoModel.isComplete = isComplete
        todoModel.isPriority = isPriority

        if (taskId > 0) {
            createTodoInter.onUpdateTodo(todoModel) {
                onResponseData(it)
            }
        } else {
            createTodoInter.onCreateTodo(todoModel) {
                onResponseData(it)
            }
        }
    }
}