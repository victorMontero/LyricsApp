package com.android.lyricsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "news"
)
data class News (

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var source: Source? = null,
    var urlToImage: String? = null,
    var url: String? = null,
    var publishedAt: String? = null
)