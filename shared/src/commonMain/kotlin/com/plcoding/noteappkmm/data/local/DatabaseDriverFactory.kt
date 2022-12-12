package com.plcoding.noteappkmm.data.local

import com.squareup.sqldelight.db.SqlDriver

//we want just create a factory that's shared which can create the correct driver for the correct platform
//expect means that the implementation will be in Android
//with this function will be implemented in both ios and android
//tha path must the same for both Android and Ios "data/local"
expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}