package com.khunchheang.todo.di.component

import android.app.Application
import com.khunchheang.todo.app.TodoApp
import com.khunchheang.todo.di.builder.ActivityBuilder
import com.khunchheang.todo.di.module.AppDataModule
import com.khunchheang.todo.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, AppDataModule::class, ActivityBuilder::class])
interface AppComponent {

    fun inject(app: TodoApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}