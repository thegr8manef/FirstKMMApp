package com.plcoding.noteappkmm.data.local

import com.plcoding.noteappkmm.database.NoteDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
//the implementation of DatabaseDriverFactory of the "commonMain"
//the word actual means this implementation for Ios
//for Ios we used NativeSQuite package
actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(NoteDatabase.Schema, "note.db")
    }
}