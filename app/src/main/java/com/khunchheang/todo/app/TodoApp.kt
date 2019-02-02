package com.khunchheang.todo.app

import android.app.Activity
import android.app.Application
import com.khunchheang.todo.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TodoApp : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

}