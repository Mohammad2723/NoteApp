package com.github.ebrahimi16153.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
                 val noteViewModel = viewModel<NoteViewModel>()  // also work
//                val noteViewModel : NoteViewModel by viewModels()

                     NoteApp(noteViewModel = noteViewModel)

            }
        }
    }
}

@Composable
fun NoteApp(noteViewModel: NoteViewModel){
    val list = noteViewModel.noteList.collectAsState().value

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