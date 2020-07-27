package com.android.lyricsapp.service

import com.android.lyricsapp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("/everything")
    suspend fun getNews() : Response<NewsResponse>
}