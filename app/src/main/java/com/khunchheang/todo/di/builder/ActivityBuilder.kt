package com.khunchheang.todo.di.builder

import com.khunchheang.todo.ui.module.CreateTodoActivityModule
import com.khunchheang.todo.ui.module.MainActivityModule
import com.khunchheang.todo.ui.mvp.view.activity.CreateTodoActivity
import com.khunchheang.todo.ui.mvp.view.activity.MainActivity
import com.khunchheang.todo.ui.scope.CreateTodoActivityScope
import com.khunchheang.todo.ui.scope.MainActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @MainActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @CreateTodoActivityScope
    @ContributesAndroidInjector(modules = [CreateTodoActivityModule::class])
    abstract fun bindAddTodoActivity(): CreateTodoActivity

}