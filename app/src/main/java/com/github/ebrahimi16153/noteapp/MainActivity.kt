package com.github.ebrahimi16153.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.ebrahimi16153.noteapp.model.Note
import com.github.ebrahimi16153.noteapp.screen.ScreenNote
import com.github.ebrahimi16153.noteapp.ui.theme.NoteAppTheme
import com.github.ebrahimi16153.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                     NoteApp()

            }
        }
    }
}

@Composable
fun NoteApp(noteViewModel: NoteViewModel = viewModel()){
    val list = noteViewModel.getNotes()

    ScreenNote(list = list, onAdd = {
        noteViewModel.addNote(it)
    }, onRemove = {
        noteViewModel.removeNote(it)
    })
}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    NoteAppTheme {
//
//    }
//}