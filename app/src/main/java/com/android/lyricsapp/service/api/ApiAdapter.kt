package com.android.lyricsapp.service.api

import com.android.lyricsapp.service.NewsAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {
    val API_CLIENT: NewsAPI = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsAPI::class.java)
}