package com.android.lyricsapp.modules.articles

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android.lyricsapp.R
import com.android.lyricsapp.modules.home.view.NewsActivity
import com.android.lyricsapp.modules.home.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.articles_fragment.*

class ArticlesFragment : Fragment(R.layout.articles_fragment) {

    lateinit var viewModel: NewsViewModel
    val args : ArticlesFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val news = args.news
        web_view_articles_fragment.apply{
            webViewClient = WebViewClient()
            loadUrl(news.url)
        }

        fab_to_favorite_articles.setOnClickListener {
            viewModel.saveNews(news)
            Snackbar.make(view, "article saved", Snackbar.LENGTH_SHORT).show()
        }
    }


}