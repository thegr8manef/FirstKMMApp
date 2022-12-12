package com.plcoding.noteappkmm.android.note_list

import com.plcoding.noteappkmm.domain.note.Note

data class NoteListState(
    //par default the list of notes is empty list
    val notes: List<Note> = emptyList(),
    //par default the search Text is ""
    val searchText: String = "",
    //par default the the search icon deactivate
    val isSearchActive: Boolean = false
)
