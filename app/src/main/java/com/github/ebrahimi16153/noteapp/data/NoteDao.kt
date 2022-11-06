package com.github.ebrahimi16153.noteapp.data

import androidx.room.*
import com.github.ebrahimi16153.noteapp.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM notes_tbl")
    fun getAllNote(): Flow<List<Note>>

    @Query("SELECT * FROM notes_tbl where id=:id")
    suspend fun getNoteById(id: String):Note

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE FROM notes_tbl")
    suspend fun deleteAll()


}