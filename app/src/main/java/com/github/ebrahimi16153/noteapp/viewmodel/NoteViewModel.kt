package com.github.ebrahimi16153.noteapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.github.ebrahimi16153.noteapp.model.Note

class NoteViewModel:ViewModel() {

    private var list = mutableStateListOf<Note>()

    fun addNote(note: Note){
        list.add(note)
    }
    fun removeNote(note: Note){
        list.remove(note)
    }
    fun getNotes():List<Note>{
        return list
    }



}