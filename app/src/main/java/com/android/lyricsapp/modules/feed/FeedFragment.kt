package com.android.lyricsapp.modules.feed

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.lyricsapp.R
import com.android.lyricsapp.adapters.NewsAdapter
import com.android.lyricsapp.modules.home.view.NewsActivity
import com.android.lyricsapp.modules.home.viewmodel.NewsViewModel
import com.bumptech.glide.load.engine.Resource
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlin.math.log


class FeedFragment : Fragment(R.layout.feed_fragment) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "NewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        viewModel.news.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is com.android.lyricsapp.util.Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is com.android.lyricsapp.util.Resource.Error ->{
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occoured: $message")
                    }
                }
                is com.android.lyricsapp.util.Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        progress_bar_feed_id.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progress_bar_feed_id.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        recycler_view_feed_id.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}