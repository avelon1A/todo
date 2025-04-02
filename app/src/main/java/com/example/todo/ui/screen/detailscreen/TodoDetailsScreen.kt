package com.example.todo.ui.screen.detailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todo.ui.viewmodel.TodoDetailsViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Composable
fun TodoDetailsScreen(navController: NavHostController, todoId: Long){
    val viewModel: TodoDetailsViewModel = koinViewModel()
    val todos by viewModel.todosDetails.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.getTodoDetails(todoId)

    }
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {

        todos?.title?.let { Text(text = it,fontSize = 20.sp) }
        todos?.status?.let { Text(text = it.toString()) }

    }

}

@Serializable
data class TodoDetailsScreen(  val id: Long)


