package util

object Routes {
    const val EventList = "event_list"
    const val EventDetails = "event_details/{eventId}"
    const val AttendeeList = "attendee_list" // No eventId parameter needed
    const val TaskList = "task_list"
    const val CreateAttendee = "create_attendee" // No eventId parameter needed
    const val CreateTask = "create_task"
    const val CreateEvent = "create_event"
    const val Dashboard = "dashboard"
    const val EventsScreen = "events_screen" // Keep if you have an EventsScreen composable
    const val AttendeesScreen = "attendees_screen" // Keep if you have an AttendeesScreen composable
    const val TasksScreen = "tasks_screen" // Keep if you have a TasksScreen composable
    const val SelectTask = "select_task"
}