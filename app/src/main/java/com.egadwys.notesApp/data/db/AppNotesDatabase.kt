package com.egadwys.notesApp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.egadwys.notesApp.common.Converter
import com.egadwys.notesApp.data.note.NoteDao
import com.egadwys.notesApp.data.note.models.Note
import com.egadwys.notesApp.data.todo.TodoDao
import com.egadwys.notesApp.data.todo.models.Todo

@Database(
    entities = [Note::class, Todo::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppNotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun toDoDao(): TodoDao

    companion object {

        @Volatile
        private var INSTANCE: AppNotesDatabase? = null

        fun getDatabase(context: Context): AppNotesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppNotesDatabase::class.java,
                        "note_marks_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}