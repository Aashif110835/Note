package com.example.note

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")

class NoteEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var description: String,
    var isLike: Boolean = false
)/*{
    @PrimaryKey(autoGenerate = true)
    var id: Int
}
*/

