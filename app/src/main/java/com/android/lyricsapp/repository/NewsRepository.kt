package com.android.lyricsapp.repository

import com.android.lyricsapp.database.NewsDatabase
import com.android.lyricsapp.service.api.RetrofitInstance

class NewsRepository(
    val db: NewsDatabase
) {
    suspend fun getNews(countryCode: String, pageNumber: Int)=
        RetrofitInstance.api.getNews(countryCode, pageNumber)
}