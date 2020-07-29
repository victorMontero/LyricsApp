package com.android.lyricsapp.service

import com.android.lyricsapp.model.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "16894722ee044069ac1ba24c6feddd08"

interface NewsApi {
    @GET("/everything")
    suspend fun getNewsAsync(@Query("q") searchTerm: String): Response<NewsResponse>
}