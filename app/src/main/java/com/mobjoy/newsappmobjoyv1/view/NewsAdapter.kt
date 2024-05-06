package com.mobjoy.newsappmobjoyv1.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobjoy.newsappmobjoyv1.R
import com.mobjoy.newsappmobjoyv1.databinding.LatestNewsItemBinding
import com.mobjoy.newsappmobjoyv1.model.articles.ArticlesItem

class NewsAdapter(var newsList : List<ArticlesItem?>? = null) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(
        val itemBinding:LatestNewsItemBinding
    ):RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val viewBinding = LatestNewsItemBinding.inflate(
          LayoutInflater.from(parent.context) , parent , false
      )
        return ViewHolder(viewBinding)
    }

    fun bindNews(new:List<ArticlesItem?>?){
        newsList = new
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = newsList?.size?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val new = newsList!![position]
        holder.itemBinding.newsTitleTV.text = new?.title.toString()
        holder.itemBinding.articleWriterTV.text = new?.content.toString()
        holder.itemBinding.durationTV.text= new?.publishedAt.toString()

        Glide.with(holder.itemView)
            .load(new?.urlToImage)
            .placeholder(R.drawable.pic)
            .into(holder.itemBinding.newsImage)

    }
}