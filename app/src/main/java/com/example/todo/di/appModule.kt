package com.example.todo.di

import android.app.Application
import androidx.room.Room
import com.example.todo.database.AppDatabase
import com.example.todo.ui.viewmodel.TodoDetailsViewModel
import com.example.todo.ui.viewmodel.TodoViewModel
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
    single { get<AppDatabase>().todoDetails() }

    viewModel { TodoViewModel(get(),get()) }
    viewModel { TodoDetailsViewModel(get()) }


}