package com.android.lyricsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.lyricsapp.R
import com.android.lyricsapp.extension.categoryStringSize
import com.android.lyricsapp.extension.convertToCalendar
import com.android.lyricsapp.extension.formatToEnCa
import com.android.lyricsapp.model.News
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val NUMBER_CHARACTER = 40


    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object  : DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    private var onItemClickListener: ((News) -> Unit)? = null

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(news.urlToImage).into(item_image)
            item_source.text = news.source?.name ?: "newsApp"
            item_date.text = news.publishedAt?.convertToCalendar()?.formatToEnCa()
            item_headline.text = news.title?.categoryStringSize(NUMBER_CHARACTER)
            item_headline.text = news.title?.categoryStringSize(NUMBER_CHARACTER)
            setOnClickListener{
                onItemClickListener?.let { it(news) }
            }
        }
    }


    fun setOnItemClickListener(listener: (News) -> Unit){
        onItemClickListener = listener
    }

}