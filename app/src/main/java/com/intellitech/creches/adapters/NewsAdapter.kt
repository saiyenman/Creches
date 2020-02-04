package com.intellitech.creches.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intellitech.creches.R
import com.intellitech.creches.models.News
import kotlinx.android.synthetic.main.newsitem.view.*

class NewsAdapter(var newsList : ArrayList<News>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    fun updateNews(newUsers: List<News>) {
        newsList.clear()
        newsList.addAll(newUsers)
        notifyDataSetChanged()
    }
    class NewsViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var item_news_author = view.item_news_author
        var item_news_Title = view.item_news_Title
        var item_news_description = view.item_news_description
        fun bind(news: News) {
            item_news_author?.text = news.author
            item_news_description?.text = news.description
            item_news_Title?.text = news.title
        }
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news: News = newsList[position]
        holder.bind(news)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.newsitem, parent, false)
        return NewsViewHolder(v)
    }
    override fun getItemCount(): Int {
        return newsList.size
    }

}