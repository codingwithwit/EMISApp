package data

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.todolistapp.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskData(private val dbHelper: SQLiteOpenHelper) {

    suspend fun getTasksFromDatabase(): List<Task> = withContext(Dispatchers.IO) {
        Log.d("TaskData", "Fetching tasks from database.")
        val tasks = mutableListOf<Task>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(TaskEntry.TABLE_NAME, null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(TaskEntry.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(TaskEntry.COLUMN_NAME))
                val isCompleted = getInt(getColumnIndexOrThrow(TaskEntry.COLUMN_IS_COMPLETED)) != 0
                tasks.add(Task(id, name, isCompleted))
            }
            close()
        }
        db.close()
        Log.d("TaskData", "Tasks fetched: ${tasks.size}")
        tasks
    }

    suspend fun checkIfTasksExist(): Boolean = withContext(Dispatchers.IO) {
        val db = dbHelper.readableDatabase
        val cursor = db.query(TaskEntry.TABLE_NAME, arrayOf("COUNT(*)"), null, null, null, null, null)
        val tasksExist = cursor.moveToFirst() && cursor.getInt(0) > 0
        cursor.close()
        db.close()
        tasksExist
    }

    fun createTask(task: Task) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(TaskEntry.COLUMN_NAME, task.name)
            put(TaskEntry.COLUMN_IS_COMPLETED, if (task.isCompleted) 1 else 0)
        }
        db.insert(TaskEntry.TABLE_NAME, null, values)
        db.close()
    }

    fun updateTaskCompletionStatus(taskId: Int, isCompleted: Boolean) { //Function "updateTaskCompletionStatus" is never used
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(TaskEntry.COLUMN_IS_COMPLETED, if (isCompleted) 1 else 0)
        }
        db.update(TaskEntry.TABLE_NAME, contentValues, "${TaskEntry.COLUMN_ID} = ?", arrayOf(taskId.toString()))
        db.close()
    }

    fun deleteTask(taskId: Int) {
        val db = dbHelper.writableDatabase
        db.delete(TaskEntry.TABLE_NAME, "${TaskEntry.COLUMN_ID} = ?", arrayOf(taskId.toString()))
        db.close()
    }
}

// Companion object for Task table
object TaskEntry {
    const val TABLE_NAME = "Tasks"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_IS_COMPLETED = "is_completed"
    // Additional column constants here
}
