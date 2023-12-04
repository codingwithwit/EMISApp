//TaskListScreen.kt
package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolistapp.Task
import data.TaskData
import kotlinx.coroutines.launch

@Composable
fun TaskListScreen(taskData: TaskData) {
    val tasks = remember { mutableStateOf(listOf<Task>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            tasks.value = taskData.getTasksFromDatabase()
        }
    }

    LazyColumn {
        items(tasks.value) { task ->
            TaskRow(
                task = task,
                taskData = taskData,
                onTaskChanged = {
                    coroutineScope.launch {
                        tasks.value = taskData.getTasksFromDatabase()
                    }
                }
            )
        }
    }
}
@Composable
fun TaskRow(
    task: Task,
    taskData: TaskData,
    onTaskChanged: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = { isChecked ->
                taskData.updateTaskCompletionStatus(task.id, isChecked)
                onTaskChanged()
            }
        )
        Text(text = task.name, modifier = Modifier.weight(1f).padding(8.dp))
        Button(onClick = {
            taskData.deleteTask(task.id)
            onTaskChanged()
        }) {
            Text("Delete")
        }
    }
}
