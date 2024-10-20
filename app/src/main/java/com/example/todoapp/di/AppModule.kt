package com.example.todoapp.di

import android.app.Application
import androidx.room.Room
import com.example.todoapp.data.local.TodoDao
import com.example.todoapp.data.local.TodoDatabase
import com.example.todoapp.domain.repository.TodoRepository
import com.example.todoapp.data.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(app, TodoDatabase::class.java, "todo_db")
            .build()
    }

    @Provides
    fun provideTodoDao(db: TodoDatabase): TodoDao {
        return db.todoDao()
    }

    @Provides
    fun provideTodoRepository(dao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(dao)
    }
}