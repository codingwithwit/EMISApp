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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolistapp.Attendee
import data.AttendeeData
import kotlinx.coroutines.launch

@Composable
fun CreateAttendeeScreen(navController: NavController, attendeeData: AttendeeData) {
    var name by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        Button(onClick = {
            coroutineScope.launch {
                attendeeData.createAttendee(Attendee(0, name)) // Assuming `Attendee` class no longer requires `eventId`
                navController.popBackStack()
            }
        }) {
            Text("Create Attendee")
        }
    }
}