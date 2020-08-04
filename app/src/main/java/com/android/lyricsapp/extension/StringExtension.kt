package com.android.lyricsapp.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.categoryStringSize(numberOfCharacter: Int): String {
    if(this.length > numberOfCharacter){
        val firstCharacter = 0
        return "${this.substring(firstCharacter, numberOfCharacter)}..."
    }
    return this
}

fun String.convertToCalendar(): Calendar {
    val canadaFormat = SimpleDateFormat("yyyy-MM-dd")
    val convertedDate: Date = canadaFormat.parse(this)
    val date = Calendar.getInstance()
    date.time = convertedDate
    return date
}