package com.example.todo.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todo.database.Todo
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Composable
fun Todo(navController: NavHostController, viewModel: TodoViewModel) {
    val todos by viewModel.todos.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd){
        LazyColumn(modifier = Modifier.fillMaxSize())
        {
            items(todos.size) { item ->
                TodoDetailsTab(todo = todos[item],  onDeleteConfirmed = { viewModel.deleteTodo(it) },
                   onClick = {todo -> navController.navigate(TodoDetailsScreen(todo.id))} )
            }
        }
        if (showDialog) {
            AddTodoDialog(onDismiss = { showDialog = false }, viewModel = viewModel)
        }

        FloatingActionButton(onClick = { showDialog = true },modifier = Modifier.padding(16.dp)) {
            Text("+")
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoDetailsTab(todo: Todo, onDeleteConfirmed: (Todo) -> Unit, onClick:(Todo) -> Unit ){
    var showDeleteDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color.LightGray)
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onClick(todo) },
                onLongClick = {
                    showDeleteDialog = true
                }
            ),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start
    ) {
        Text(todo.title, style = MaterialTheme.typography.titleLarge,modifier = Modifier.padding(10.dp))
        Text(todo.details, modifier = Modifier.padding(start = 10.dp,bottom = 10.dp,end = 10.dp))
    }
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Todo?") },
            text = { Text("Are you sure you want to delete this todo?") },
            confirmButton = {
                Button(onClick = {
                    onDeleteConfirmed(todo)
                    showDeleteDialog = false
                }, colors = ButtonColors(
                    containerColor = Color.Red,
                    contentColor =  Color.Black,
                    disabledContainerColor =  Color.Red,
                    disabledContentColor =  Color.Red
                )) {
                    Text("Delete" ,color = Color.Black)
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteDialog = false }, colors = ButtonColors(
                    containerColor = Color.LightGray,
                    contentColor =  Color.Black,
                    disabledContainerColor =  Color.LightGray,
                    disabledContentColor =  Color.LightGray
                ) ) {
                    Text("Cancel",color = Color.Black)
                }
            }
        )
    }
}

@Composable
fun AddTodoDialog(onDismiss: () -> Unit, viewModel: TodoViewModel) {
    var title by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Add Todo") },
        text = {
            Column {
                Text("Title of Todo")
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Text("Enter your Todo details here.")
                TextField(value = details, onValueChange = { details = it }
                    ,  colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ))
            }
        },
        confirmButton = {
            Button(onClick = {
                onDismiss()
                viewModel.addTodo(Todo(title = title, details = details))
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}


@Serializable
object TodoScreen