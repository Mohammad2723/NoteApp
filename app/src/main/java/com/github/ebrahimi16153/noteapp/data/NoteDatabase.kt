package com.github.ebrahimi16153.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.ebrahimi16153.noteapp.model.Note
import com.github.ebrahimi16153.noteapp.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(UUIDConverter::class)
abstract class NoteDatabase:RoomDatabase() {
     abstract fun noteDao():NoteDao

}