package com.android.lyricsapp.database

import com.android.lyricsapp.database.response.FeedResponse
import com.bumptech.glide.manager.ConnectivityMonitor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "cba9107da24962656b3cee67c6d029ad"

interface VagalumeApiService {

    @GET("artist")
    fun getSearchResponse (
        //@Query("apiKey") apiKey : String,
        @Query("q") term : String = "drake"
        //@Query("limit") limit : Int
    ) : Deferred<FeedResponse>

    companion object {
        operator fun invoke(): VagalumeApiService {
            val requestInterceptor = Interceptor{chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.vagalume.com.br/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VagalumeApiService::class.java)
        }
    }
}