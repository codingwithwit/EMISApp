//SelectTaskScreen.kt
package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolistapp.Task
import data.TaskData
import kotlinx.coroutines.launch

@Composable
fun SelectTaskScreen(navController: NavController, taskData: TaskData) {
    val tasks = remember { mutableStateOf(listOf<Task>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            tasks.value = taskData.getTasksFromDatabase()
        }
    }

    Column {
        LazyColumn {
            items(tasks.value) { task ->
                TaskItem(task = task, onDelete = {
                    coroutineScope.launch {
                        taskData.deleteTask(task.id)
                        tasks.value = taskData.getTasksFromDatabase()
                    }
                })
            }
        }

        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

@Composable
fun TaskItem(task: Task, onDelete: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(text = task.name, modifier = Modifier.weight(1f))
        IconButton(onClick = onDelete) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Task")
        }
    }
}
