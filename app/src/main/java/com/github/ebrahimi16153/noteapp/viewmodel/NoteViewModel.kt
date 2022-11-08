package com.github.ebrahimi16153.noteapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.ebrahimi16153.noteapp.model.Note
import com.github.ebrahimi16153.noteapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(private  val repository: NoteRepository) :ViewModel() {

    private var _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllNotes().distinctUntilChanged().collect(){ list->
                if(list.isEmpty()){
                    Log.d("EMPTY LIST","list is empty")
                }else{
                    _noteList.value = list
                }
            }
        }

    }


    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note = note) }
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note = note) }
    fun updateNote(note:Note) = viewModelScope.launch { repository.updateNote(note = note) }
    fun removeAllNotes(note: Note) = viewModelScope.launch { repository.deleteAll() }




}