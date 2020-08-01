package com.android.lyricsapp.modules.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.lyricsapp.R
import com.android.lyricsapp.adapters.NewsAdapter
import com.android.lyricsapp.modules.home.view.NewsActivity
import com.android.lyricsapp.modules.home.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.favorites_fragment.*
import kotlinx.android.synthetic.main.feed_fragment.*

class FavoritesFragment : Fragment(R.layout.favorites_fragment) {

    lateinit var viewModel : NewsViewModel
    lateinit var newsAdapter : NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("news", it)
            }
            findNavController().navigate(
                R.id.action_favoritesFragment_to_articlesFragment,
                bundle
            )
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val news = newsAdapter.differ.currentList[position]
                viewModel.deleteNews(news)
                Snackbar.make(view, "sucessfull deleted news", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        viewModel.saveNews(news)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(recycler_view_favorites_id)
        }

        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer {news ->
            newsAdapter.differ.submitList(news)


        })
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        recycler_view_favorites_id.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}