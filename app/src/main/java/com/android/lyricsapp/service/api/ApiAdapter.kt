package com.android.lyricsapp.service.api

import com.android.lyricsapp.service.NewsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {
    val apiClient: NewsApi = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)
}