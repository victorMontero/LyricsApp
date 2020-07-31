package com.android.lyricsapp.modules.articles

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.android.lyricsapp.R
import com.android.lyricsapp.modules.home.view.NewsActivity
import com.android.lyricsapp.modules.home.viewmodel.NewsViewModel

class ArticlesFragment : Fragment(R.layout.articles_fragment) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }


}