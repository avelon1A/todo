package com.example.todo.ui.screen.mainscreen

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.todo.database.Todo
import com.example.todo.ui.viewmodel.TodoViewModel
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CreateTodo(
    onDismiss: () -> Unit,
    viewModel: TodoViewModel
) {
    var title by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }
    val selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val selectedDocumentUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val timeStamp = remember {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(
            Date()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
        ) {
            androidx.compose.material3.Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(32.dp).clickable { onDismiss() }
            )
            Text(
                text = "Create Todo",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )}

        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            label = { Text("Todo Title") },
            singleLine = false,
            value = title, onValueChange = { title = it }, modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            label = { Text("Todo Details") },
            singleLine = false,
            value = details,
            onValueChange = { details = it },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.7f)
        )


        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                (context as? Activity)?.startActivityForResult(intent, 100)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = buttonColors(
                Color.Black,
                Color.White
            )
        ) {
            Text("Pick Image")
        }


        selectedImageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Selected Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                    type = "*/*"
                }
                (context as? Activity)?.startActivityForResult(intent, 101)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = buttonColors(
                Color.Black,
                Color.White
            )
        ) {
            Text("Attach Document")
        }

        selectedDocumentUri?.let {
            Text(text = "Selected Document: ${it.lastPathSegment}", fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Created At: $timeStamp", fontSize = 12.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.addTodo(
                    Todo(
                        title = title,
                        details = details,
                    )
//                    imageUri = selectedImageUri,
//                    documentUri = selectedDocumentUri,
//                    createdAt = timeStamp
                )
                onDismiss()
            },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp),
            colors = buttonColors(
                Color.Black,
                Color.White
            ),

            enabled = title.isNotEmpty()
        ) {
            Text("Save Todo")
        }
    }
}


@Serializable
object CreateTodo