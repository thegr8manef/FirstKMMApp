package com.plcoding.noteappkmm.domain.note

interface NoteDataSource {
    //specify all the functions we need to access our local database
    suspend fun insertNote(note: Note)
    suspend fun getNoteById(id: Long): Note?
    suspend fun getAllNotes(): List<Note>
    suspend fun deleteNoteById(id: Long)
}