package com.android.lyricsapp.modules.feed

import androidx.lifecycle.ViewModel
import com.android.lyricsapp.repository.NewsRepository

class FeedViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

}