package com.android.lyricsapp.modules.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.android.lyricsapp.R
import com.android.lyricsapp.database.NewsDatabase
import com.android.lyricsapp.modules.feed.NewsViewModelProviderFactory
import com.android.lyricsapp.modules.home.viewmodel.NewsViewModel
import com.android.lyricsapp.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        val newsRepository = NewsRepository(NewsDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        bottom_navigation_view.setupWithNavController(navController)

        //NavigationUI.setupActionBarWithNavController(this, navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}