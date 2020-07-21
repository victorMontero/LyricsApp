package com.android.lyricsapp.database.response


import com.google.gson.annotations.SerializedName

data class Rank(
    val period: Int,
    val points: String,
    val pos: String,
    val uniques: String,
    val views: String
)