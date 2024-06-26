package com.egadwys.notesApp.ui.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.egadwys.notesApp.data.note.models.Note
import com.egadwys.notesApp.data.note.models.Priority
import java.text.SimpleDateFormat
import java.util.*

class NoteSharedViewModel(application: Application) : AndroidViewModel(application) {

    val emptyNotes: MutableLiveData<Boolean> = MutableLiveData(true)
    val mPriority: MutableLiveData<Priority> = MutableLiveData(Priority.LOW)

    fun checkNotesIfEmpty(notes: List<Note>) {
        emptyNotes.value = notes.isEmpty()
    }

    fun validationNoteForm(title: String, content: String): Boolean {
        return !(title.isEmpty() || content.isEmpty())
    }

    fun parsePriority(priority: String) {
        when (priority) {
            "High priority" -> mPriority.value = Priority.HIGH
            "Medium priority" -> mPriority.value = Priority.MEDIUM
            "Low priority" -> mPriority.value = Priority.LOW
        }
    }

    fun getCurrentDate(): String {
        val current = Date()
        val formatter = SimpleDateFormat("EEE d MMM yy, k:mm", Locale.getDefault())
        return formatter.format(current)
    }

}