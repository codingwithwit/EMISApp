// EventListScreen.kt //package ui
package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolistapp.Event
import data.EventData
import kotlinx.coroutines.launch

@Composable
fun EventListScreen(navController: NavController, eventData: EventData) {
    val events = remember { mutableStateOf(listOf<Event>()) }
    val coroutineScope = rememberCoroutineScope()

    // Load the events when the composable is first launched
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            events.value = eventData.getAllEvents()
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Button to create a new event
        Button(onClick = { navController.navigate("create_event") }) {
            Text("Create New Event")
        }

        // List the events
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(events.value) { event ->
                EventItem(event, eventData, onEventDeleted = {
                    // Refresh the list after deleting an event
                    coroutineScope.launch {
                        events.value = eventData.getAllEvents()
                    }
                })
            }
        }
    }
}

@Composable
fun EventItem(event: Event, eventData: EventData, onEventDeleted: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(
            text = event.title, // Unresolved reference: title
            modifier = Modifier
                .weight(1f)
                .clickable { /* Navigate to event details */ },
            style = MaterialTheme.typography.titleMedium
        )
        IconButton(onClick = {
            eventData.deleteEvent(event.id)
            onEventDeleted()
        }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
