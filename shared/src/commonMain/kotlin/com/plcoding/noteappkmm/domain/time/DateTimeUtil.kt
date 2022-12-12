package com.plcoding.noteappkmm.domain.time

import kotlinx.datetime.*

object DateTimeUtil {

    //function for current time
    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }
    //function that transform ime to milliseconds
    fun toEpochMillis(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }
    //function that do the format Date
    fun formatNoteDate(dateTime: LocalDateTime): String {
        //took only 3 letters from months and make the first letter uppercase
        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        //if date of month {1..9} format => "01..09" then format =>"10..30"
        val day = if(dateTime.dayOfMonth < 10) "0${dateTime.dayOfMonth}" else dateTime.dayOfMonth
        val year = dateTime.year
        //if hour {0..9} format => "01..09" then format =>"10..23"
        val hour = if(dateTime.hour < 10) "0${dateTime.hour}" else dateTime.hour
        //if minute {0..9} format => "01..09" then format =>"10..59"
        val minute = if(dateTime.minute < 10) "0${dateTime.minute}" else dateTime.minute
         //the format of the date for example "Nov 14 2022, 11:06"
        return buildString {
            append(month)
            append(" ")
            append(day)
            append(" ")
            append(year)
            append(", ")
            append(hour)
            append(":")
            append(minute)
        }
    }
}