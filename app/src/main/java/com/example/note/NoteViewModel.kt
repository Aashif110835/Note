package com.example.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.note.database.NoteDatabase
import com.example.note.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val allNote: LiveData<List<NoteEntity>>
    val allFavNote: LiveData<List<NoteEntity>>
    private var noteRepository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        noteRepository = NoteRepository(dao)
        allNote = noteRepository.getAllNote()
        allFavNote = noteRepository.getFavNote()
    }

    fun addNote(noteEntity: NoteEntity){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addNote(noteEntity)
        }
    }

    fun delNote(noteEntity: NoteEntity){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.delNote(noteEntity)
        }
    }

    fun updateNote(noteEntity: NoteEntity){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateNote(noteEntity)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteAll()
        }
    }

    fun addLike(noteEntity: NoteEntity) {
        viewModelScope.launch {
            noteRepository.addLike(noteEntity.id!!,noteEntity.title,noteEntity.description,noteEntity.isLike)
        }
    }
}