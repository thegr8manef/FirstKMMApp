package com.plcoding.noteappkmm.data.note

import com.plcoding.noteappkmm.database.NoteDatabase
import com.plcoding.noteappkmm.domain.note.Note
import com.plcoding.noteappkmm.domain.note.NoteDataSource
import com.plcoding.noteappkmm.domain.time.DateTimeUtil

class SqlDelightNoteDataSource(db: NoteDatabase): NoteDataSource {
    //access the queries
    private val queries = db.noteQueries

    //function to insert Note
    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            created = DateTimeUtil.toEpochMillis(note.created)
        )
    }
    //function Search by ID
    override suspend fun getNoteById(id: Long): Note? {
        return queries
            .getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }
    //function that return all the notes from database
    override suspend fun getAllNotes(): List<Note> {
        return queries
            .getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }
    //function that delete any note from database
    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}