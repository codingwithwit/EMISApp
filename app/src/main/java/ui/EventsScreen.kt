package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import data.EventData
import util.Routes

@Composable
fun EventsScreen(navController: NavController, eventData: EventData) {
    val eventsExist = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        eventsExist.value = eventData.checkIfEventsExist()
    }

    Column {
        Button(onClick = { navController.navigate(Routes.CreateEvent) }) {
            Text("Create Event")
        }

        Button(onClick = { navController.navigate(Routes.Dashboard) }) {
            Text("Back to Dashboard")
        }
    }
}