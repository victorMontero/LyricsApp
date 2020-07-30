package com.android.lyricsapp.database

import androidx.room.TypeConverter
import com.android.lyricsapp.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name)
    }
}