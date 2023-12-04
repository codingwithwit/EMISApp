//TaskScreen.kt
package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import data.TaskData
import kotlinx.coroutines.launch
import util.Routes

@Composable
fun TasksScreen(navController: NavController, taskData: TaskData) { //Function "TaskScreen" is never used
    val tasksExist = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            tasksExist.value = taskData.checkIfTasksExist()
        }
    }

    Column {
        Button(onClick = { navController.navigate(Routes.CreateTask) }) {
            Text("Create Task")
        }

        if (tasksExist.value) {
            Button(onClick = { navController.navigate(Routes.SelectTask) }) {
                Text("Select Task")
            }
        }

        Button(onClick = { navController.navigate(Routes.TaskList) }) {
            Text("Task List")
        }
    }
}
