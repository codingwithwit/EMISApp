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
import com.example.todolistapp.Event
import data.EventData
import kotlinx.coroutines.launch

@Composable
fun CreateEventScreen(navController: NavController, eventData: EventData) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        TextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
        TextField(value = date, onValueChange = { date = it }, label = { Text("Date") })
        Button(onClick = {
            coroutineScope.launch {
                eventData.createEvent(Event(0, title, description, date))
                navController.popBackStack()
            }
        }) {
            Text("Create Event")
        }
    }
}