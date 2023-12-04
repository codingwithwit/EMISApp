// AttendeesListScreen.kt
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
import com.example.todolistapp.Attendee
import data.AttendeeData
import kotlinx.coroutines.launch

@Composable
fun AttendeesListScreen(navController: NavController, attendeeData: AttendeeData) {
    val attendees = remember { mutableStateOf(listOf<Attendee>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            attendees.value = attendeeData.getAllAttendees()
        }
    }

    Column {
        attendees.value.forEach { attendee ->
            Text("Name: ${attendee.name}")
        }

        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}