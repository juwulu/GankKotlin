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
import com.jwl.gank.R
import com.jwl.gank.activity.ArticleActivity
import com.jwl.gank.bean.xiandu.article.ResultsItem

/**
 * author:  lujunwu
 * date:    2019/1/4 11:24
 * desc:    NoDiscription
 */
public class ReadAdapter(var ctx: Context, var results: MutableList<ResultsItem>) : RecyclerView.Adapter<ReadAdapter.VH>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ReadAdapter.VH {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_article_layout, null, false) //To change body of created functions use File | Settings | File Templates.
        return VH(v)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(vh: ReadAdapter.VH, position: Int) {
        val resultsItem = results[position]
        vh.timeTv.setText("时间：${resultsItem.publishedAt.substring(0, 10)}")
        vh.authorTv.setText("标签：${resultsItem.site.name}")
        vh.titleTv.setText(resultsItem.title)
        var url: String = ""
        if (resultsItem.cover != null) {
            if (isPic(resultsItem.cover)) {
                url = resultsItem.cover
            }else{
                url = resultsItem.cover
            }
        }
        if (url.length == 0) {
            vh.imgIv.setVisibility(View.GONE)
        }

        if (resultsItem.cover!=null) {
            Glide.with(ctx).load(url).apply(RequestOptions().placeholder(R.drawable.placeholder)).into(vh.imgIv)
        }
        vh.rootCl.setOnClickListener {
            val intent = Intent(ctx, ArticleActivity::class.java)
            intent.putExtra("url", getRealUrl(resultsItem.url))
            intent.putExtra("title", resultsItem.title)
            intent.putExtra("publish", resultsItem.publishedAt)
            intent.putExtra("author", resultsItem.site.name)
            if (resultsItem.cover != null && resultsItem.cover.length > 0) {
                intent.putExtra("imgUrl", resultsItem.cover)
            } else {
                intent.putExtra("imgUrl", "")
            }
            intent.putExtra("isArticle", false)
            ctx.startActivity(intent)
        }
    }


    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        var titleTv: TextView = v.findViewById(R.id.article_title_tv)
        var authorTv: TextView = v.findViewById(R.id.article_author_tv);
        var timeTv: TextView = v.findViewById(R.id.article_time_tv)
        var imgIv: ImageView = v.findViewById(R.id.article_img_iv)
        var rootCl: ConstraintLayout = v.findViewById(R.id.root_cl)
    }

    fun isPic(url: String): Boolean {
        if (url.endsWith("jpg") || url.endsWith("jpeg") || url.endsWith("gif") || url.endsWith("png")) {
            return true
        }
        return false
    }

    fun getRealUrl(url:String):String{
        if (url==null) {
            return ""
        }
        val index = url.indexOf("?")
        if (index == -1) {
            return url
        }
        return url.substring(0,index)
    }
}