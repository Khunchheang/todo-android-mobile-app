package com.khunchheang.todo.di.builder

import com.khunchheang.todo.ui.module.MainActivityModule
import com.khunchheang.todo.ui.mvp.view.activity.MainActivity
import com.khunchheang.todo.ui.scope.MainActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @MainActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindLoginActivity(): MainActivity

}