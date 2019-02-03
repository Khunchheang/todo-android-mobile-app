package com.khunchheang.todo.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import com.khunchheang.todo.R
import com.khunchheang.todo.data.local.AppDatabase
import com.khunchheang.todo.di.DatabaseInfo
import com.khunchheang.todo.ui.base.fragment.DialogLoadingSingleton
import com.khunchheang.todo.ui.scope.CreateTodoActivityScope
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideCurrentCalendar(): Calendar = Calendar.getInstance()

    @Provides
    @Singleton
    fun provideCurrentDate(calendar: Calendar): Date {
        return GregorianCalendar(
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH],
            0,
            0
        ).time
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @DatabaseInfo
    @Singleton
    internal fun provideDatabaseName(context: Context): String = context.getString(R.string.db_name)

    @Provides
    @Singleton
    fun provideIntent(): Intent = Intent()

    @Provides
    @Singleton
    fun provideDialogLoading(): DialogLoadingSingleton = DialogLoadingSingleton.instance
}