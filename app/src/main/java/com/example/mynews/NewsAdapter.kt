package com.example.mynews

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context,val articles:List<Article>):RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){
    class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var img=itemView.findViewById<ImageView>(R.id.image)
        var title=itemView.findViewById<TextView>(R.id.title)
        var description=itemView.findViewById<TextView>(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.news_layout,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
      val article=articles[position]
        holder.title.text=article.title
        holder.description.text=article.description
        Glide.with(context).load(article.urlToImage).into(holder.img)
     holder.itemView.setOnClickListener {
val intent= Intent(context,WebActivity::class.java)
    intent.putExtra("URL",article.url)
    context.startActivity(intent)
}
    }

    override fun getItemCount(): Int {
      return articles.size
    }
}