package com.khunchheang.todo.ui.mvp.interactorimpl

import com.khunchheang.todo.data.local.dao.TaskDao
import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.base.basemvp.response.ExceptionResponseModel
import com.khunchheang.todo.ui.base.basemvp.response.ResponseModel
import com.khunchheang.todo.ui.base.basemvp.response.SuccessResponseModel
import com.khunchheang.todo.ui.mvp.CreateTodo

class CreateTodoInteractorImpl(private val todoDao: TaskDao) : CreateTodo.CreateTodoInteractor {

    override fun onCreateTodo(todoModel: TaskModel, completion: (responseModel: ResponseModel) -> Unit) {
        try {
            val id = todoDao.insert(todoModel)
            todoModel.id = id

            completion(SuccessResponseModel(todoModel))
        } catch (e: Exception) {
            e.localizedMessage
            completion(ExceptionResponseModel("Cannot create task"))
        }
    }

    override fun onUpdateTodo(todoModel: TaskModel, completion: (responseModel: ResponseModel) -> Unit) {
        try {
            todoDao.updateTask(todoModel)
            completion(SuccessResponseModel(todoModel))
        } catch (e: Exception) {
            e.localizedMessage
            completion(ExceptionResponseModel("Cannot create task"))
        }
    }

    override fun onCancelLoading() {

    }
}