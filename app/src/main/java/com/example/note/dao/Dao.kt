package com.example.note.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.note.NoteEntity

@Dao
interface Dao {

    @Query("SELECT * FROM note")
    fun getAllNote():LiveData<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE isLike")
    fun getFavNote():LiveData<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(noteEntity: NoteEntity)

    @Delete
    suspend fun delNote(noteEntity: NoteEntity)

    @Query("DELETE FROM note")
    suspend fun deleteAll()

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Query("UPDATE note SET title=:title,description=:description,isLike=:isLike WHERE id=:id")
    suspend fun addLike(id: Int, title: String, description: String, isLike: Boolean)
}