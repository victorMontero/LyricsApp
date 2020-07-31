package com.android.lyricsapp.modules.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.android.lyricsapp.R
import com.android.lyricsapp.modules.home.view.NewsActivity
import com.android.lyricsapp.modules.home.viewmodel.NewsViewModel

class FavoritesFragment : Fragment(R.layout.favorites_fragment) {

    lateinit var viewModel : NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }
}