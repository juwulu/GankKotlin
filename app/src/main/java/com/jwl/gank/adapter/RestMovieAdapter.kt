package com.jwl.gank.adapter

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jwl.gank.R
import com.jwl.gank.activity.ArticleActivity
import com.jwl.gank.bean.ResultsItem

class RestMovieAdapter(var ctx: Context, var result: MutableList<ResultsItem>) : RecyclerView.Adapter<RestMovieAdapter.ViewHolder>() {

    override fun onBindViewHolder(vh: ViewHolder, p1: Int) {
        val resultBean = result.get(p1)
        vh.timeTv.setText("时间：${resultBean.publishedAt.substring(0, 10)}")
        vh.authorTv.setText("来源：${resultBean.who}")
        vh.titleTv.setText(resultBean.desc)

        vh.rootCl.setOnClickListener {
            val intent = Intent(ctx, ArticleActivity::class.java)
            intent.putExtra("url",resultBean.url)
            intent.putExtra("title",resultBean.desc)
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return result.size//To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_movie_layout, null, false) //To change body of created functions use File | Settings | File Templates.
        return ViewHolder(v)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var titleTv: TextView = v.findViewById(R.id.article_title_tv)
        var authorTv: TextView = v.findViewById(R.id.article_author_tv);
        var timeTv: TextView = v.findViewById(R.id.article_time_tv)
        var rootCl:ConstraintLayout = v.findViewById(R.id.root_cl)
    }
}