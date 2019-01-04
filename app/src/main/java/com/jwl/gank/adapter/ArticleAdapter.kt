package com.jwl.gank.adapter

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jwl.gank.GlideApp
import com.jwl.gank.R
import com.jwl.gank.activity.ArticleActivity
import com.jwl.gank.bean.Result

class ArticleAdapter(var ctx: Context, var result: MutableList<Result>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onBindViewHolder(vh: ViewHolder, p1: Int) {
        val resultBean = result.get(p1)
        vh.timeTv.setText("时间：${resultBean.publishedAt.substring(0, 10)}")
        vh.authorTv.setText("来源：${resultBean.source}")
        vh.titleTv.setText(resultBean.desc)
        var url: String = ""
        if (resultBean.images != null) {
            if (resultBean.images.size > 0) {
                url = resultBean.images[0]
            }
        }
        if (url.length == 0) {
            vh.imgIv.setVisibility(View.GONE)
        }
        Glide.with(ctx).load(url).apply(RequestOptions().placeholder(R.drawable.placeholder)).into(vh.imgIv)
        vh.rootCl.setOnClickListener {
            val intent = Intent(ctx, ArticleActivity::class.java)
            intent.putExtra("url",resultBean.url)
            intent.putExtra("title",resultBean.desc)
            intent.putExtra("publish",resultBean.publishedAt)
            intent.putExtra("author",resultBean.who)
            if (resultBean.images!=null&&resultBean.images.size>0) {
                intent.putExtra("imgUrl",resultBean.images[0])
            }else{
                intent.putExtra("imgUrl","")
            }
            intent.putExtra("isArticle",true)
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return result.size//To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_article_layout, null, false) //To change body of created functions use File | Settings | File Templates.
        return ViewHolder(v)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var titleTv: TextView = v.findViewById(R.id.article_title_tv)
        var authorTv: TextView = v.findViewById(R.id.article_author_tv);
        var timeTv: TextView = v.findViewById(R.id.article_time_tv)
        var imgIv: ImageView = v.findViewById(R.id.article_img_iv)
        var rootCl:ConstraintLayout = v.findViewById(R.id.root_cl)
    }
}