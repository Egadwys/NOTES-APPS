package com.egadwys.notesApp.common

import androidx.room.TypeConverter
import com.egadwys.notesApp.data.note.models.Priority

class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }

}