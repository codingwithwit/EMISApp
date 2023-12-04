package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import data.AttendeeData
import data.EventData
import data.TaskData
import ui.ScreenMain
import util.DatabaseHelper


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbHelper = DatabaseHelper(this)
        val attendeeData = AttendeeData(dbHelper)
        val eventData = EventData(dbHelper)
        val taskData = TaskData(dbHelper)

        setContent {
            MaterialTheme {
                // Call ScreenMain here and pass required dependencies
                ScreenMain(
                    attendeeData = attendeeData,
                    eventData = eventData,
                    taskData = taskData,
                )
            }
        }
    }
}

