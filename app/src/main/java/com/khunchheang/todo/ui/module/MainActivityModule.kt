package com.khunchheang.todo.ui.module

import com.khunchheang.todo.data.model.TodoModel
import com.khunchheang.todo.di.DatabaseDebugUrl
import com.khunchheang.todo.ui.scope.MainActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @MainActivityScope
    fun provideTodoModel(): TodoModel = TodoModel()

    @Provides
    @MainActivityScope
    @DatabaseDebugUrl
    fun provideDebugDbUrl(): String = "Khunchheang"
}