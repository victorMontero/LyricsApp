package com.android.lyricsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "news"
)
data class News (

    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var title: String?,
    var description: String?,
    var source: Source?,
    var urlToImage: String?,
    var url: String?,
    var publishedAt: String?
): Serializable