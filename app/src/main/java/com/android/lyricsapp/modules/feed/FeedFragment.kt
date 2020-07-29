package com.android.lyricsapp.modules.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.lyricsapp.R
import com.android.lyricsapp.service.api.ApiAdapter
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class FeedFragment : Fragment(), CoroutineScope by MainScope() {

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

        launch(Dispatchers.Main){
            try{
                val response = ApiAdapter.apiClient.getNewsAsync("drake")
                if (response.isSuccessful && response.body() != null){
                    val data = response.body()!!
                    data.articles?.toString().let {
                        frame_layout_text_view.text = it
                    }
                } else {
                    frame_layout_text_view.text = "erroU"
                }
            } catch (e: Exception){
                frame_layout_text_view.text = "erro $e"
            }
        }



    }

}