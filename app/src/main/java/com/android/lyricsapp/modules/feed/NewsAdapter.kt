package com.android.lyricsapp.modules.feed

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.lyricsapp.databinding.ItemNewsBinding
import com.android.lyricsapp.model.News
import com.bumptech.glide.Glide

class NewsAdapter(private val listener: NewsItemListener) : RecyclerView.Adapter<NewsViewHolder>() {

    interface NewsItemListener {
        fun onClickedNews(newsUrl: String)
    }

    private val items = ArrayList<News>()

    fun setItems(item: ArrayList<News>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding: ItemNewsBinding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bind(items[position])
}

class NewsViewHolder (private val itemBinding: ItemNewsBinding,
                      private val listener: NewsAdapter.NewsItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
View.OnClickListener {

    private lateinit var news: News

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: News){
        this.news = item
        itemBinding.itemHeadline.text = item.title
        Glide.with(itemBinding.root)
            .load(item.urlToImage)
            .into(itemBinding.itemImage)
    }

    override fun onClick(v: View?) {
        listener.onClickedNews(news.url.toString())
    }

}
