package com.android.lyricsapp.extension

import java.util.*

fun Calendar.formatToEnCa(): String {
    val formatCA = "yyyy-MM-dd"
    val dateFormat = java.text.SimpleDateFormat(formatCA)
    return dateFormat.format(this.time)
}