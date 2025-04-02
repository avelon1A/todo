package com.example.todo.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.todo.ui.screen.detailscreen.TodoDetailsScreen
import com.example.todo.ui.screen.mainscreen.CreateTodo
import com.example.todo.ui.screen.mainscreen.TodoScreen
import com.example.todo.ui.screen.splashScreen.SplashScreen
import com.example.todo.ui.viewmodel.TodoViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    NavHost(navController = navController,
        modifier = modifier,
        startDestination = SplashScreen
    ) {
        composable<TodoScreen>(
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { 2200 }, animationSpec = tween(500)) }
        ) {
            val viewModel: TodoViewModel = koinViewModel()
            TodoScreen(navController, viewModel)
        }
        composable<TodoDetailsScreen>(
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1200 }, animationSpec = tween(1000)) }
        ) {
         val data = it.toRoute<TodoDetailsScreen>()
            TodoDetailsScreen(navController = navController, todoId = data.id)
        }
        composable<SplashScreen>{
            SplashScreen(onAnimationFinish = {navController.navigate(TodoScreen)})
        }
        composable<CreateTodo>(
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { 2200 }, animationSpec = tween(500)) }
        ) {

            val viewModel: TodoViewModel = koinViewModel()
            CreateTodo(onDismiss = { navController.popBackStack() }, viewModel)
        }
    }

}
