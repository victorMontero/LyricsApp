package com.android.lyricsapp.database

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.android.lyricsapp.model.Source

//@TypeConverters(Converters::class)
class Converters {
    @TypeConverter
    fun fromSource(source: com.android.lyricsapp.model.Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name)
    }
}