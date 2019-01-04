package com.jwl.gank.adapter

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jwl.gank.R
import com.jwl.gank.activity.ArticleActivity
import com.jwl.gank.room.favorite.Favorite
import com.jwl.gank.room.read.Read

/**
 * author:  lujunwu
 * date:    2018/12/21 09:20
 * desc:    NoDiscription
 */

public class FavoriteAdapter<T>(var ctx:Context,var favorites:MutableList<T>): RecyclerView.Adapter<FavoriteAdapter<T>.VH>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VH {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_article_layout, null, false) //To change body of created functions use File | Settings | File Templates.
        return VH(v);
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(vh: VH, p1: Int) {
        val favorite = favorites.get(p1)
        if (favorite is Favorite) {
            vh.timeTv.setText("时间：${favorite.publishTime.substring(0, 10)}")
            vh.authorTv.setText("来源：${favorite.source}")
            vh.titleTv.setText(favorite.title)
            var url: String = ""
            if (favorite.imgUrl != null) {
                url = favorite.imgUrl
            }
            if (url.length == 0) {
                vh.imgIv.setVisibility(View.GONE)
            }
            Glide.with(ctx).load(url).apply(RequestOptions().placeholder(R.drawable.placeholder)).into(vh.imgIv)
            vh.rootCl.setOnClickListener {
                val intent = Intent(ctx, ArticleActivity::class.java)
                intent.putExtra("url",favorite.url)
                intent.putExtra("title",favorite.title)
                intent.putExtra("publish",favorite.publishTime)
                intent.putExtra("author",favorite.source)
                if (favorite.imgUrl!=null&&favorite.imgUrl.length>0) {
                    intent.putExtra("imgUrl",favorite.imgUrl)
                }else{
                    intent.putExtra("imgUrl","")
                }
                intent.putExtra("isArticle",true)
                ctx.startActivity(intent)
            }
        }else if (favorite is Read){
            vh.timeTv.setText("时间：${favorite.publishTime.substring(0, 10)}")
            vh.authorTv.setText("来源：${favorite.source}")
            vh.titleTv.setText(favorite.title)
            var url: String = ""
            if (favorite.imgUrl != null) {
                url = favorite.imgUrl
            }
            if (url.length == 0) {
                vh.imgIv.setVisibility(View.GONE)
            }
            Glide.with(ctx).load(url).apply(RequestOptions().placeholder(R.drawable.placeholder)).into(vh.imgIv)
            vh.rootCl.setOnClickListener {
                val intent = Intent(ctx, ArticleActivity::class.java)
                intent.putExtra("url",favorite.url)
                intent.putExtra("title",favorite.title)
                intent.putExtra("publish",favorite.publishTime)
                intent.putExtra("author",favorite.source)
                if (favorite.imgUrl!=null&&favorite.imgUrl.length>0) {
                    intent.putExtra("imgUrl",favorite.imgUrl)
                }else{
                    intent.putExtra("imgUrl","")
                }
                intent.putExtra("isArticle",true)
                ctx.startActivity(intent)
            }
        }

    }

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        var titleTv: TextView = v.findViewById(R.id.article_title_tv)
        var authorTv: TextView = v.findViewById(R.id.article_author_tv);
        var timeTv: TextView = v.findViewById(R.id.article_time_tv)
        var imgIv: ImageView = v.findViewById(R.id.article_img_iv)
        var rootCl: ConstraintLayout = v.findViewById(R.id.root_cl)
    }



}