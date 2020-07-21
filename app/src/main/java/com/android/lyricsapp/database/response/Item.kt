package com.android.lyricsapp.database.response


import com.google.gson.annotations.SerializedName

data class Item(
    val desc: String,
    val id: String,
    val url: String,
    val year: String
)