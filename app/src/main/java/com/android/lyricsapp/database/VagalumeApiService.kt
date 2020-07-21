package com.android.lyricsapp.database

import com.android.lyricsapp.database.response.FeedResponse
import com.bumptech.glide.manager.ConnectivityMonitor
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "cba9107da24962656b3cee67c6d029ad"

interface VagalumeApiService {

    @GET("search.art")
    fun getSearchResponse (
        @Query("apiKey") apiKey : String,
        @Query("q") term : String = "drake",
        @Query("limit") limit : Int
    ) : Deferred<FeedResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): VagalumeApiService {
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
                .addInterceptor(co)
        }
    }
}