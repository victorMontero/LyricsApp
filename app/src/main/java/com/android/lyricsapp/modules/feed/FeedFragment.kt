package com.android.lyricsapp.modules.feed

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.lyricsapp.R
import com.android.lyricsapp.adapters.NewsAdapter
import com.android.lyricsapp.modules.home.view.NewsActivity
import com.android.lyricsapp.modules.home.viewmodel.NewsViewModel
import com.android.lyricsapp.util.Constants.Companion.QUERY_PAGE_SIZE
import kotlinx.android.synthetic.main.feed_fragment.*


class FeedFragment : Fragment(R.layout.feed_fragment) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "NewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("news", it)
            }
            findNavController().navigate(
                R.id.action_feedFragment_to_articlesFragment,
                bundle
            )
        }

        viewModel.news.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is com.android.lyricsapp.util.Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.newsPage == totalPages
                        if(isLastPage){
                            recycler_view_feed_id.setPadding(0,0,0,0)
                        }
                    }
                }
                is com.android.lyricsapp.util.Resource.Error -> {
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
        isLoading = false
    }

    private fun showProgressBar() {
        progress_bar_feed_id.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage &&
                    isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if (shouldPaginate){
                viewModel.getNews("br")
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        recycler_view_feed_id.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@FeedFragment.scrollListener)
        }
    }
}