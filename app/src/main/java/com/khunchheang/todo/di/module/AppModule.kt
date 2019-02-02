package com.khunchheang.todo.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.khunchheang.todo.R
import com.khunchheang.todo.data.local.AppDatabase
import com.khunchheang.todo.di.DatabaseInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

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
}