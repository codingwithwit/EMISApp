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
import data.AttendeeData
import kotlinx.coroutines.launch
import util.Routes

@Composable
fun DashboardScreen(navController: NavController, attendeeData: AttendeeData) {
    val attendeesExist = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            attendeesExist.value = attendeeData.checkIfAttendeesExist()
        }
    }

    Column {
        Button(onClick = { navController.navigate(Routes.EventsScreen) }) {
            Text("Events")
        }
        Button(onClick = { navController.navigate(Routes.AttendeesScreen) }) {
            Text("Attendees")
        }
        Button(onClick = { navController.navigate(Routes.TasksScreen) }) {
            Text("Tasks")
        }
        if (attendeesExist.value) {
            Button(onClick = { navController.navigate(Routes.AttendeeList) }) {
                Text("Attendees List")
            }
        }
    }
}
