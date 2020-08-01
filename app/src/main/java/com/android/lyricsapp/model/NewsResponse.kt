package com.android.lyricsapp.model

data class NewsResponse (
    var articles: MutableList<News>,
    val totalResults: Int
)
