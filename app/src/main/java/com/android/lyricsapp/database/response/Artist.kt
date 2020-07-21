package com.android.lyricsapp.database.response


import com.google.gson.annotations.SerializedName

data class Artist(
    val albums: Albums,
    val desc: String,
    val genre: List<Genre>,
    val id: String,
    val lyrics: Lyrics,
    @SerializedName("pic_medium")
    val picMedium: String,
    @SerializedName("pic_small")
    val picSmall: String,
    val rank: Rank,
    val related: List<Related>,
    val toplyrics: Toplyrics,
    val url: String
)