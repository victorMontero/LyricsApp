package com.android.lyricsapp.repository

import com.android.lyricsapp.database.NewsDatabase
import com.android.lyricsapp.model.News
import com.android.lyricsapp.service.api.RetrofitInstance

class NewsRepository(
    val db: NewsDatabase
) {
    suspend fun getNews(countryCode: String, pageNumber: Int)=
        RetrofitInstance.api.getNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(news: News) = db.getNewsDao().insert(news)

    fun getSavedNews() = db.getNewsDao().getAllNews()

    suspend fun deleteNews(news: News) = db.getNewsDao().deleteNews(news)
}