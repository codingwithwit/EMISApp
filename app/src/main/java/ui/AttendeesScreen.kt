// AttendeesScreen.kt
package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import util.Routes

@Composable
fun AttendeesScreen(navController: NavController) { // Removed the 'attendeeData' parameter
    Column {
        Button(onClick = { navController.navigate(Routes.CreateAttendee) }) {
            Text("Create Attendee")
        }
        Button(onClick = { navController.navigate(Routes.AttendeeList) }) {
            Text("Attendee List")
        }
        Button(onClick = { navController.navigate(Routes.Dashboard) }) {
            Text("Back to Dashboard")
        }
    }
}
