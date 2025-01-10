package com.example.todo

import android.app.Application
import androidx.room.Room
import com.example.todo.database.AppDatabase
import com.example.todo.ui.TodoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            get<Application>(),
            AppDatabase::class.java,
            "TodoDatabase"
        ).build()
    }

    single { get<AppDatabase>().todoDao() }

    viewModel { TodoViewModel(get()) }


}