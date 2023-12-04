// CreateTaskScreen.kt
package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolistapp.Task
import data.TaskData

@Composable
fun CreateTaskScreen(navController: NavController, taskData: TaskData) {
    var taskName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = taskName, onValueChange = { taskName = it }, label = { Text("Task Name") })
        Button(onClick = {
            taskData.createTask(Task(0, taskName, false)) //Unresolved refference: creaTeask
            navController.popBackStack()
        }) {
            Text("Create Task")
        }
    }
}
