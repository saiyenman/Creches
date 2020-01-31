package com.intellitech.creches.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intellitech.creches.R
import com.intellitech.creches.models.News
import kotlinx.android.synthetic.main.newsitem.view.*

class NewsAdapter(val items : ArrayList<News>, val context: Context): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.newsitem, parent, false))

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.item_news_description.text=items.get(position).description
        holder.item_news_author.text=items.get(position).author
        holder.item_news_Title.text=items.get(position).title
        }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_news_author = itemView.item_news_author
        val item_news_Title=itemView.item_news_Title
        val item_news_description=itemView.item_news_description
    }
}