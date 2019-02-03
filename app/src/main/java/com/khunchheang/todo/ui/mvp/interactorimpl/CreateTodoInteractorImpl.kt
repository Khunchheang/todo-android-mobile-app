package com.khunchheang.todo.ui.mvp.interactorimpl

import com.khunchheang.todo.data.local.dao.TaskDao
import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.base.basemvp.baseinteractorimpl.BaseInteractorImpl
import com.khunchheang.todo.ui.mvp.CreateTodo

class CreateTodoInteractorImpl(private val todoDao: TaskDao) : BaseInteractorImpl<TaskModel>(),
    CreateTodo.CreateTodoInteractor {

    override fun onCreateTodo(todoModel: TaskModel) {
        try {
            val id = todoDao.insert(todoModel)
            todoModel.id = id
            responseListener?.onSuccess(todoModel)
        } catch (e: Exception) {
            e.localizedMessage
            responseListener?.onException("Cannot create task")
        }
    }

    override fun onUpdateTodo(todoModel: TaskModel) {
        try {
            todoDao.updateTask(todoModel)
            responseListener?.onSuccess(todoModel)
        } catch (e: Exception) {
            e.localizedMessage
            responseListener?.onException("Cannot update task")
        }
    }

    override fun onCancelLoading() {

    }
}