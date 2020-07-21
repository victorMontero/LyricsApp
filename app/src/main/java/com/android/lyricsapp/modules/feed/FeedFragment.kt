package com.android.lyricsapp.modules.feed

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.lyricsapp.R
import com.android.lyricsapp.database.API_KEY
import com.android.lyricsapp.database.VagalumeApiService
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FeedFragment : Fragment() {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private lateinit var viewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.feed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        // TODO: Use the ViewModel
        val apiService = VagalumeApiService()

        GlobalScope.launch(Dispatchers.Main) {
            val artistResponse = apiService.getSearchResponse( "drake").await()
            frame_layout_text_view.text = artistResponse.artist.lyrics.toString()
        }
    }

}