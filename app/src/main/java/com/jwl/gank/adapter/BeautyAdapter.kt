package com.jwl.gank.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jwl.gank.R
import com.jwl.gank.bean.Result

class BeautyAdapter(var ctx: Context,var results:MutableList<Result> ) : RecyclerView.Adapter<BeautyAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(vh: ViewHolder, p1: Int) {
        val result = results[p1]
        Glide.with(ctx).load(result.url).apply(RequestOptions().placeholder(R.drawable.placeholder)).into(vh.beautyIv)
//        vh.downloadIv.setOnClickListener(object :View.OnClickListener{
//            override fun onClick(v: View?) {
//                Toast.makeText(ctx,"下载成功",Toast.LENGTH_LONG)
//            }
//        })
//        vh.favoriteIv.setOnClickListener(object :View.OnClickListener{
//            override fun onClick(v: View?) {
//                Toast.makeText(ctx,"收藏成功",Toast.LENGTH_LONG)
//            }
//        })
        vh.beautyIv.setOnLongClickListener{ Toast.makeText(ctx,"$p1",Toast.LENGTH_SHORT).show(); true }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_beauty_layout, p0, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        var beautyIv: ImageView = v.findViewById(R.id.beauty_iv)
//        var downloadIv:ImageView = v.findViewById(R.id.download_iv)
//        var favoriteIv:ImageView = v.findViewById(R.id.favorite_iv)
    }
}


