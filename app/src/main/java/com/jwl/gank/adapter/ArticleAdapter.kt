package com.jwl.gank.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jwl.gank.R
import com.jwl.gank.bean.Result

public class ArticleAdapter(var ctx: Context,var result:MutableList<Result>): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onBindViewHolder(vh: ViewHolder, p1: Int) {
        val resultBean = result.get(p1)
        vh.timeTv.setText("时间：${resultBean.createdAt}")
        vh.authorTv.setText("来源：${resultBean.source}")
        vh.titleTv.setText(resultBean.desc)
        Glide.with(ctx).load(resultBean.images.get(0)).into(vh.imgIv)
    }

    override fun getItemCount(): Int {
        return result.size//To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_article_layout, null, false) //To change body of created functions use File | Settings | File Templates.
        return ViewHolder(v);
    }

    inner class ViewHolder(v:View) : RecyclerView.ViewHolder(v) {
        var titleTv: TextView = v.findViewById(R.id.article_title_tv)
        var authorTv:TextView =v.findViewById(R.id.article_author_tv);
        var timeTv:TextView = v.findViewById(R.id.article_time_tv)
        var imgIv: ImageView = v.findViewById(R.id.article_img_iv)
    }
}