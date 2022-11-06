package com.github.ebrahimi16153.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.ebrahimi16153.noteapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {

     abstract fun noteDao():NoteDao

}