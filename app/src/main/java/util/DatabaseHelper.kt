// DatabaseHelper.kt
package util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MaterialThemeDatabase.db"

        // Task table
        const val TABLE_TASKS = "Tasks"
        const val COLUMN_TASK_ID = "id"
        const val COLUMN_TASK_NAME = "name"
        const val COLUMN_TASK_IS_COMPLETED = "isCompleted"

        // Event table
        const val TABLE_EVENTS = "Events"
        const val COLUMN_EVENT_ID = "id"
        const val COLUMN_EVENT_TITLE = "title"
        const val COLUMN_EVENT_DESCRIPTION = "description"
        const val COLUMN_EVENT_DATE = "date"

        // Attendee table
        const val TABLE_ATTENDEES = "Attendees"
        const val COLUMN_ATTENDEE_ID = "id"
        const val COLUMN_ATTENDEE_NAME = "name"
        const val COLUMN_ATTENDEE_EVENT_ID = "event_id"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Task table creation
        db.execSQL("""
            CREATE TABLE $TABLE_TASKS (
                $COLUMN_TASK_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TASK_NAME TEXT NOT NULL,
                $COLUMN_TASK_IS_COMPLETED INTEGER NOT NULL
            );
        """.trimIndent())

        // Event table creation
        db.execSQL(
            """
            CREATE TABLE $TABLE_EVENTS (
                $COLUMN_EVENT_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EVENT_TITLE TEXT NOT NULL,
                $COLUMN_EVENT_DESCRIPTION TEXT NOT NULL,
                $COLUMN_EVENT_DATE TEXT NOT NULL
            );
            """.trimIndent()
        )

        // Attendee table creation
        db.execSQL(
            """
            CREATE TABLE $TABLE_ATTENDEES (
                $COLUMN_ATTENDEE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_ATTENDEE_NAME TEXT NOT NULL,
                $COLUMN_ATTENDEE_EVENT_ID INTEGER NOT NULL,
                FOREIGN KEY($COLUMN_ATTENDEE_EVENT_ID) REFERENCES $TABLE_EVENTS($COLUMN_EVENT_ID)
            );
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle any upgrades if your database version changes
    }
}
