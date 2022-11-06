package com.github.ebrahimi16153.noteapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ebrahimi16153.noteapp.R
import com.github.ebrahimi16153.noteapp.components.NoteButton
import com.github.ebrahimi16153.noteapp.components.TextInput
import com.github.ebrahimi16153.noteapp.model.Note
import com.github.ebrahimi16153.noteapp.viewmodel.NoteViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScreenNote(
    list: List<Note>,
    onAdd: (Note) -> Unit,
    onRemove: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = TextStyle(color = Color.White)
                )
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    tint = Color.White,
                    contentDescription = "icon"
                )
            },
            backgroundColor = Color(0xff007aff)
        )

        // text input

        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextInput(text = title, label = "Title", maxLine = 1, onTextChange = { onText ->
                if (onText.all {
                        it.isLetter() || it.isWhitespace()
                    }) {
                    title = onText
                }
            })
            Spacer(modifier = Modifier.height(8.dp))
            TextInput(
                text = description,
                label = "Add a note",
                maxLine = 5,
                onTextChange = { onText ->
                    if (onText.all { it.isLetter() || it.isWhitespace() }) {
                        description = onText
                    }
                })
            Spacer(modifier = Modifier.height(8.dp))
            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    onAdd(Note(title = title, description = description))
                }
            })
        }

        Divider(modifier = Modifier.padding(20.dp))
        LazyColumn(modifier = Modifier.padding(horizontal = 20.dp)) {
            items(items = list) { note ->

                NoteRow(note = note, onClick = {
                    onRemove(it)
                })
            }
        }
    }


}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onClick: (Note) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(RoundedCornerShape(topEnd = 10.dp)),
        color = Color(0xff007aff)
    ) {
        Column(modifier = modifier
            .padding(5.dp)
            .clickable { onClick(note) }) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2.copy(color = Color.White)
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1.copy(color = Color.White)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    ScreenNote(list = NoteViewModel().getNotes(), onAdd = {}, onRemove = {} )
}
