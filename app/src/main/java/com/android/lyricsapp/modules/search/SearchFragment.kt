package com.android.lyricsapp.modules.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.lyricsapp.R
import com.android.lyricsapp.modules.home.view.NewsActivity
import com.android.lyricsapp.modules.home.viewmodel.NewsViewModel

class SearchFragment : Fragment(R.layout.search_fragment) {

    lateinit var viewModel : NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }



}