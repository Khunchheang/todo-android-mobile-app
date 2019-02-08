package com.khunchheang.todo.ui.module

import android.content.Context
import com.khunchheang.todo.ui.adapter.OptionSetAdapter
import com.khunchheang.todo.ui.mvp.CreateTodo
import com.khunchheang.todo.ui.mvp.DeleteTask
import com.khunchheang.todo.ui.mvp.GetTask
import com.khunchheang.todo.ui.mvp.OptionsSet
import com.khunchheang.todo.ui.mvp.presenterimpl.CreateTodoPresenterImpl
import com.khunchheang.todo.ui.mvp.presenterimpl.DeleteTaskPresenterImpl
import com.khunchheang.todo.ui.mvp.presenterimpl.GetTaskPresenterImpl
import com.khunchheang.todo.ui.mvp.presenterimpl.OptionSetPresenterImpl
import com.khunchheang.todo.ui.scope.CreateTodoActivityScope
import dagger.Module
import dagger.Provides

@Module
class CreateTodoActivityModule {

    @Provides
    @CreateTodoActivityScope
    fun provideOptionsSetAdapter(context: Context): OptionSetAdapter {
        return OptionSetAdapter(context, ArrayList())
    }

    @Provides
    @CreateTodoActivityScope
    fun provideCreateTodoPresenter(createTodoInter: CreateTodo.CreateTodoInteractor): CreateTodo.CreateTodoPresenter {
        return CreateTodoPresenterImpl(createTodoInter)
    }

    @Provides
    @CreateTodoActivityScope
    fun provideOptionSetPresenter(optionSetInter: OptionsSet.OptionSetInteractor): OptionsSet.OptionSetPresenter {
        return OptionSetPresenterImpl(optionSetInter)
    }

    @Provides
    @CreateTodoActivityScope
    fun provideGetTaskPre(getTaskInter: GetTask.GetTaskInteractor): GetTask.GetTaskPresenter {
        return GetTaskPresenterImpl(getTaskInter)
    }

    @Provides
    @CreateTodoActivityScope
    fun provideDeleteTaskPre(deleteTaskInter: DeleteTask.DeleteTaskInteractor): DeleteTask.DeleteTaskPresenter {
        return DeleteTaskPresenterImpl(deleteTaskInter)
    }

}