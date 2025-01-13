package com.example.todo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.koin.androidx.compose.koinViewModel
import java.util.UUID


@Composable
fun TodoDetailsScreen(navController: NavHostController, todoId: Long){
    val viewModel: TodoDetailsViewModel = koinViewModel()
    val todos by viewModel.todosDetails.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.getTodoDetails(todoId)

    }
    Column(modifier = Modifier.fillMaxSize()) {

        todos?.description?.let { Text(text = it,fontSize = 20.sp) }
        todos?.status?.let { Text(text = it.toString()) }

    }

}

@Serializable
data class TodoDetailsScreen(  val id: Long)


