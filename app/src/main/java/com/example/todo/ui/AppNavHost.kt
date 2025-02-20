package com.example.todo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.todo.database.TodoDetails
import org.koin.androidx.compose.koinViewModel


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    NavHost(navController = navController,
        modifier = modifier,
        startDestination = TodoScreen
    ) {
        composable<TodoScreen> {
            val viewModel:TodoViewModel = koinViewModel()
             Todo(navController,viewModel)
        }
        composable<TodoDetailsScreen> {
         val data = it.toRoute<TodoDetailsScreen>()
            TodoDetailsScreen(navController = navController,todoId =  data.id)
        }
    }
    
}
