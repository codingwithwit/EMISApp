package ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import data.AttendeeData
import data.EventData
import data.TaskData
import util.Routes

@Composable
fun ScreenMain(
    attendeeData: AttendeeData,
    eventData: EventData,
    taskData: TaskData
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Dashboard) {
        composable(Routes.Dashboard) {
            DashboardScreen(navController, attendeeData)
        }
        composable(Routes.EventList) {
            EventListScreen(navController, eventData)
        }
        composable(Routes.CreateEvent) {
            CreateEventScreen(navController, eventData)
        }
        composable(Routes.EventDetails + "/{eventId}",
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt("eventId") ?: throw IllegalStateException("Event ID is required")
            EventDetailScreen(navController, eventData, eventId)
        }

        composable(Routes.AttendeesScreen) {
            AttendeesScreen(navController) // AttendeesScreen without the attendeeData parameter
        }

        composable(Routes.CreateAttendee) {
            CreateAttendeeScreen(navController, attendeeData)
        }
        composable(Routes.AttendeeList) {
            AttendeesListScreen(navController, attendeeData)
        }
        composable(Routes.CreateTask) {
            CreateTaskScreen(navController, taskData)
        }
        composable(Routes.TaskList) {
            TaskListScreen(taskData)
        }
        composable(Routes.TasksScreen) {
            TasksScreen(navController, taskData)
        }
        composable(Routes.SelectTask) {
            SelectTaskScreen(navController, taskData)
        }
        composable(Routes.EventsScreen) {
            EventsScreen(navController, eventData) // Make sure this function exists
        }
        // Add other composable routes as needed
    }
}
