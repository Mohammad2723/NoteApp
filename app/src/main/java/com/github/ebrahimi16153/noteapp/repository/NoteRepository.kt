package com.github.ebrahimi16153.noteapp.repository

import com.github.ebrahimi16153.noteapp.data.NoteDao
import com.github.ebrahimi16153.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun addNote(note: Note) = noteDao.addNote(note = note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note = note)
    suspend fun deleteNote(note: Note) = noteDao.delete(note = note)
    suspend fun deleteAll() = noteDao.deleteAll()
    fun getAllNotes():Flow<List<Note>> = noteDao.getAllNote().flowOn(Dispatchers.IO).conflate()

}