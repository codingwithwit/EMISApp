package data

import android.content.ContentValues
import com.example.todolistapp.Attendee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import util.DatabaseHelper

class AttendeeData(private val dbHelper: DatabaseHelper) {

    suspend fun getAllAttendees(): List<Attendee> = withContext(Dispatchers.IO) {
        val attendees = mutableListOf<Attendee>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_ATTENDEES,
            null, null, null, null, null, null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ATTENDEE_ID))
                val name = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ATTENDEE_NAME))
                attendees.add(Attendee(id, name)) // Updated to reflect new Attendee structure
            }
            close()
        }
        db.close()
        attendees
    }

    suspend fun checkIfAttendeesExist(): Boolean = withContext(Dispatchers.IO) {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_ATTENDEES, arrayOf("COUNT(*)"), null, null, null, null, null
        )
        val attendeesExist = cursor.moveToFirst() && cursor.getInt(0) > 0
        cursor.close()
        db.close()
        attendeesExist
    }

    suspend fun createAttendee(attendee: Attendee) = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ATTENDEE_NAME, attendee.name)
        }
        db.insert(DatabaseHelper.TABLE_ATTENDEES, null, values)
        db.close()
    }
}
