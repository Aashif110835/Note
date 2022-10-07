package com.example.note.repository

import androidx.lifecycle.LiveData
import com.example.note.dao.Dao
import com.example.note.NoteEntity

class NoteRepository(private val dao: Dao) {

    fun getAllNote():LiveData<List<NoteEntity>>{
        return dao.getAllNote()
    }

    fun getFavNote():LiveData<List<NoteEntity>>{
        return dao.getFavNote()
    }

    suspend fun addNote(noteEntity: NoteEntity){
        dao.addNote(noteEntity)
    }

    suspend fun updateNote(noteEntity: NoteEntity){
        dao.updateNote(noteEntity)
    }

    suspend fun delNote(noteEntity: NoteEntity){
        dao.delNote(noteEntity)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }

    suspend fun addLike(id: Int, title: String, description: String, isLike: Boolean){
        dao.addLike(id,title,description,isLike)
    }
}