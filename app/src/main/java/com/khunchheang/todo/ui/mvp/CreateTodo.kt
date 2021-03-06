package com.khunchheang.todo.ui.mvp

import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.BaseMvpView
import com.khunchheang.todo.ui.base.basemvp.BasePresenter
import java.util.*

interface CreateTodo {

    interface CreateTodoInteractor : BaseInteractor<TaskModel> {
        fun onCreateTodo(todoModel: TaskModel)

        fun onUpdateTodo(todoModel: TaskModel)
    }

    interface CreateTodoPresenter : BasePresenter<CreateTodoView> {
        fun createTodo(taskId: Long, task: String, dueDate: Date, isPriority: Boolean, isComplete: Boolean)
    }

    interface CreateTodoView : BaseMvpView {
        fun onCreateTodoSuccess(todoModel: TaskModel)
    }
}