package com.android.lyricsapp.modules.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.lyricsapp.model.News
import com.android.lyricsapp.model.NewsResponse
import com.android.lyricsapp.repository.NewsRepository
import com.android.lyricsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    val news: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var newsPage = 1
    var newsResponse : NewsResponse? = null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse : NewsResponse? = null

    init {
        getNews("br")
    }

    fun getNews(countryCode: String) = viewModelScope.launch {
        news.postValue(Resource.Loading())
        val response = newsRepository.getNews(countryCode, newsPage)
        news.postValue(handleNewsResponse(response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                newsPage++
                if(newsResponse == null){
                    newsResponse = resultResponse
                } else {
                    val oldArticles = newsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(newsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                searchNewsPage++
                if(searchNewsResponse == null){
                    searchNewsResponse = resultResponse
                } else {
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveNews(news: News) = viewModelScope.launch {
        newsRepository.upsert(news)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteNews(news: News) = viewModelScope.launch {
        newsRepository.deleteNews(news)
    }


}