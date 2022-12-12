package com.plcoding.noteappkmm.domain.note

import com.plcoding.noteappkmm.domain.time.DateTimeUtil

class SearchNotes {

    fun execute(notes: List<Note>, query: String): List<Note> {
        //if the user write nothing
        if(query.isBlank()) {
            return notes
        }
        //if he tried something the function will search by title AND content of note
        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase()) ||
                    it.content.trim().lowercase().contains(query.lowercase())
            //After Search, the function will return the result that sorted by date of creation of notes
        }.sortedBy {
            DateTimeUtil.toEpochMillis(it.created)
        }
    }
}