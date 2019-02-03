package com.khunchheang.todo.di.module

import android.content.Context
import com.khunchheang.todo.data.local.AppDatabase
import com.khunchheang.todo.ui.mvp.*
import com.khunchheang.todo.ui.mvp.interactorimpl.*
import com.khunchheang.todo.ui.scope.CreateTodoActivityScope
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class AppDataModule {

    @Provides
    @Singleton
    fun provideCreateTodoInter(appDatabase: AppDatabase): CreateTodo.CreateTodoInteractor {
        return CreateTodoInteractorImpl(appDatabase.todoDao())
    }

    @Provides
    @Singleton
    fun provideOptionSetInter(context: Context, currentDate: Date): OptionsSet.OptionSetInteractor {
        return OptionSetInteractorImpl(context, currentDate)
    }

    @Provides
    @Singleton
    fun provideTaskListInter(currentDate: Date, appDatabase: AppDatabase): GetTaskList.TaskListInteractor {
        return TaskListInteractorImpl(currentDate, appDatabase.todoDao())
    }

    @Provides
    @Singleton
    fun provideGetTaskInter(appDatabase: AppDatabase): GetTask.GetTaskInteractor {
        return GetTaskInteractorImpl(appDatabase.todoDao())
    }

    @Provides
    @Singleton
    fun provideDeleteTaskInter(appDatabase: AppDatabase): DeleteTask.DeleteTaskInteractor {
        return DeleteTaskInteractorImpl(appDatabase.todoDao())
    }
}