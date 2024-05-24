package com.egadwys.notesApp.ui.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.egadwys.notesApp.data.db.AppNotesDatabase
import com.egadwys.notesApp.data.note.NoteRepository
import com.egadwys.notesApp.data.note.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteDao = AppNotesDatabase.getDatabase(application).noteDao()
    private val repository: NoteRepository = NoteRepository(noteDao)

    val getAllNotes = repository.notesFlow.asLiveData()
    val sortByHighPriority = repository.sortByHighPriority.asLiveData()
    val sortByLowPriority = repository.sortByLowPriority.asLiveData()

    fun insertData(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNotes()
        }
    }

    fun searchNote(searchQuery: String) =
        repository.searchNote(searchQuery).asLiveData()
}