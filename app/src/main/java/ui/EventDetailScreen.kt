// EventDetailScreen.kt
package ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolistapp.Event
import data.EventData


@Composable
fun EventDetailScreen(navController: NavController, eventData: EventData, eventId: Int) {
    val event = remember { mutableStateOf<Event?>(null) }

    LaunchedEffect(key1 = eventId) {
        val fetchedEvent = eventData.getEventById(eventId)
        if (fetchedEvent != null) {
            event.value = fetchedEvent
        } else {
            Log.e("EventDetailScreen", "Event not found for ID: $eventId")
            // Navigate back to the previous screen
            navController.popBackStack()
        }
    }

    event.value?.let { eventDetail ->
        // Display event details
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(eventDetail.title, style = MaterialTheme.typography.headlineMedium)
            Text(eventDetail.description)
            Text("Date: ${eventDetail.date}")
            // Other event details...
        }
    }
}