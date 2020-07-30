package com.android.lyricsapp.database.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.android.lyricsapp.model.News

@Dao
interface NewsDao {

    @androidx.room.Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: News): Long

    @Delete
    suspend fun deleteNews(news: News)
}