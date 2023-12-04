package data

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import com.example.todolistapp.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import util.DatabaseHelper

class EventData(private val dbHelper: SQLiteOpenHelper) {

    suspend fun checkIfEventsExist(): Boolean = withContext(Dispatchers.IO) {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_EVENTS, arrayOf("COUNT(*)"), null, null, null, null, null
        )
        val eventsExist = cursor.moveToFirst() && cursor.getInt(0) > 0
        cursor.close()
        db.close()
        return@withContext eventsExist
    }

    // Create event method should be here
    suspend fun createEvent(event: Event) = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_EVENT_TITLE, event.title) // Unresolved reference: title
            put(DatabaseHelper.COLUMN_EVENT_DESCRIPTION, event.description)
            put(DatabaseHelper.COLUMN_EVENT_DATE, event.date)
        }
        db.insert(DatabaseHelper.TABLE_EVENTS, null, values)
        db.close()
    }
    suspend fun getAllEvents(): List<Event> = withContext(Dispatchers.IO) {
        val events = mutableListOf<Event>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_EVENTS, null, null, null, null, null, null
        )
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_EVENT_ID))
                val title = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_EVENT_TITLE))
                val description = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_EVENT_DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_EVENT_DATE))
                events.add(Event(id, title, description, date))
            }
            close()
        }
        db.close()
        events
    }

    fun getEventById(eventId: Int): Event? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_EVENTS,
            null,
            "${DatabaseHelper.COLUMN_EVENT_ID} = ?",
            arrayOf(eventId.toString()),
            null,
            null,
            null
        )

        var event: Event? = null
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EVENT_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EVENT_TITLE))
            val description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EVENT_DESCRIPTION))
            val date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EVENT_DATE))
            event = Event(id, title, description, date)
        }
        cursor.close()
        db.close()
        return event
    }

    fun deleteEvent(eventId: Int) { //deleteEvent greyed out
        val db = dbHelper.writableDatabase
        db.delete(
            DatabaseHelper.TABLE_EVENTS,
            "${DatabaseHelper.COLUMN_EVENT_ID} = ?",
            arrayOf(eventId.toString())
        )
        db.close()
    }
}
