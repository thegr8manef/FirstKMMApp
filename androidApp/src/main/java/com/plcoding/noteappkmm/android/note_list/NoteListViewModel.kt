package com.plcoding.noteappkmm.android.note_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.noteappkmm.domain.note.Note
import com.plcoding.noteappkmm.domain.note.NoteDataSource
import com.plcoding.noteappkmm.domain.note.SearchNotes
import com.plcoding.noteappkmm.domain.time.DateTimeUtil
import com.plcoding.noteappkmm.presentation.RedOrangeHex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
//Hilt is for dependency injection(hilt it's a java specific dependency)
@HiltViewModel
class NoteListViewModel @Inject constructor(
    //NoteDataSource is coming form our shared module
    private val noteDataSource: NoteDataSource,
    //savedSatedHandle to get navigation arguments works like shared preferences(Restore our state)
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val searchNotes = SearchNotes()
    //initial the search functions with saved state handle
    private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

    //if one of the combine changed we will recalculate our state
    val state = combine(notes, searchText, isSearchActive) { notes, searchText, isSearchActive ->
        NoteListState(
            notes = searchNotes.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
        //stateIn makes us to always catch the latest value
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())
    //this function update the value and save state handle will allow us to restore it after process death
    fun loadNotes() {
        viewModelScope.launch {
            savedStateHandle["notes"] = noteDataSource.getAllNotes()
        }
    }
//update the state
    fun onSearchTextChange(text: String) {
        savedStateHandle["searchText"] = text
    }
//toggle the search and state handle is search Active
    fun onToggleSearch() {
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if(!isSearchActive.value) {
            savedStateHandle["searchText"] = ""
        }
    }
//delete the note by id and load the notes
    fun deleteNoteById(id: Long) {
        viewModelScope.launch {
            noteDataSource.deleteNoteById(id)
            loadNotes()
        }
    }
}