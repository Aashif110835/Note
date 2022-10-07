package com.example.note.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.note.dao.Dao
import com.example.note.NoteEntity

@Database(entities = [NoteEntity::class], version = 2, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao() : Dao

    companion object {
        @Volatile
        var INSTANCE: NoteDatabase?=null

        fun getDatabase(context: Context) : NoteDatabase {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val roomDatabaseInstance = Room.databaseBuilder(context, NoteDatabase::class.java,"note").build()
                INSTANCE = roomDatabaseInstance
                 return roomDatabaseInstance
            }
        }
    }
}