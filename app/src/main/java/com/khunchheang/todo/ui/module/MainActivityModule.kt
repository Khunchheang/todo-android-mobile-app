package com.khunchheang.todo.ui.module

import android.content.Context
import com.khunchheang.todo.ui.adapter.TaskAdapter
import com.khunchheang.todo.ui.mvp.GetTaskList
import com.khunchheang.todo.ui.mvp.ShareApp
import com.khunchheang.todo.ui.mvp.presenterimpl.ShareAppPresenterImpl
import com.khunchheang.todo.ui.mvp.presenterimpl.TaskListPresenterImpl
import com.khunchheang.todo.ui.scope.MainActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @MainActivityScope
    fun provideTaskAdapter(context: Context): TaskAdapter {
        return TaskAdapter(context, ArrayList())
    }

    @Provides
    @MainActivityScope
    fun provideTaskListPre(taskListInter: GetTaskList.TaskListInteractor): GetTaskList.TaskListPreesnter {
        return TaskListPresenterImpl(taskListInter)
    }

    @Provides
    @MainActivityScope
    fun provideShareAppPre(shareAppInter: ShareApp.ShareAppInteractor): ShareApp.ShareAppPresenter {
        return ShareAppPresenterImpl(shareAppInter)
    }
}