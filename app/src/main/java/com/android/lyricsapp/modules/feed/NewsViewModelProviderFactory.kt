package com.android.lyricsapp.modules.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.lyricsapp.modules.home.viewmodel.NewsViewModel
import com.android.lyricsapp.repository.NewsRepository

class NewsViewModelProviderFactory(
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}