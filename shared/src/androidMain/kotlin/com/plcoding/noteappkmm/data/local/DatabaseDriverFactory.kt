package com.plcoding.noteappkmm.data.local

import android.content.Context
import com.plcoding.noteappkmm.database.NoteDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
//the implementation of DatabaseDriverFactory of the "commonMain"
//the word actual means this implementation for Android
//for Android we used AndroidSQuite package
actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(NoteDatabase.Schema, context, "note.db")
    }
}