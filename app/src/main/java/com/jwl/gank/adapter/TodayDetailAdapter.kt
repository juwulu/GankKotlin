package com.jwl.gank.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jwl.gank.R
import com.jwl.gank.activity.ArticleActivity
import com.jwl.gank.bean.today.*

/**
 * author:  lujunwu
 * date:    2019/1/3 11:00
 * desc:    NoDiscription
 */
public class TodayDetailAdapter<T>(var ctx: Context, var datas: List<T>) : RecyclerView.Adapter<TodayDetailAdapter<T>.VH>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TodayDetailAdapter<T>.VH {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_today_detail_layout, null, false)
        return VH(v)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(vh: TodayDetailAdapter<T>.VH, p1: Int) {
        val item = datas.get(p1)
        var url = ""
        var title = ""
        var publish = ""
        var author = ""
        var imgUrl = ""

        if (item is AndroidItem) {
            vh.titleTv.setText(item.desc)
            val images = item.images
            if (images != null && images.size > 0) {
                vh.imgRv.layoutManager = GridLayoutManager(ctx, 2)
                vh.imgRv.adapter = ImgAdapter(ctx, images)
                imgUrl = images[0]
            }
            url = item.url
            title = item.desc
            publish = item.publishedAt
            author = item.who
        } else if (item is IOSItem) {
            vh.titleTv.setText(item.desc)
            val images = item.images
            if (images != null && images.size > 0) {
                vh.imgRv.layoutManager = GridLayoutManager(ctx, 2)
                vh.imgRv.adapter = ImgAdapter(ctx, images)
                imgUrl = images[0]
            }
            url = item.url
            title = item.desc
            publish = item.publishedAt
            author = item.who
        } else if (item is 前端Item) {
            vh.titleTv.setText(item.desc)
            val images = item.images
            if (images != null && images.size > 0) {
                vh.imgRv.layoutManager = GridLayoutManager(ctx, 2)
                vh.imgRv.adapter = ImgAdapter(ctx, images)
                imgUrl = images[0]
            }
            url = item.url
            title = item.desc
            publish = item.publishedAt
            author = item.who
        } else if (item is AppItem) {
            vh.titleTv.setText(item.desc)
            val images = item.images
            if (images != null && images.size > 0) {
                vh.imgRv.layoutManager = GridLayoutManager(ctx, 2)
                vh.imgRv.adapter = ImgAdapter(ctx, images)
                imgUrl = images[0]
            }
            url = item.url
            title = item.desc
            publish = item.publishedAt
            author = item.who
        } else if (item is 瞎推荐Item) {
            vh.titleTv.setText(item.desc)
            val images = item.images
            if (images != null && images.size > 0) {
                vh.imgRv.layoutManager = GridLayoutManager(ctx, 2)
                vh.imgRv.adapter = ImgAdapter(ctx, images)
                imgUrl = images[0]
            }
            url = item.url
            title = item.desc
            publish = item.publishedAt
            author = item.who
        } else if (item is 休息视频Item) {
            vh.titleTv.setText(item.desc)
            val images = item.images
            if (images != null && images.size > 0) {
                vh.imgRv.layoutManager = GridLayoutManager(ctx, 2)
                vh.imgRv.adapter = ImgAdapter(ctx, images)
                imgUrl = images[0]
            }
            url = item.url
            title = item.desc
            publish = item.publishedAt
            author = item.who
        } else if (item is 拓展资源Item) {
            vh.titleTv.setText(item.desc)
            val images = item.images
            if (images != null && images.size > 0) {
                vh.imgRv.layoutManager = GridLayoutManager(ctx, 2)
                vh.imgRv.adapter = ImgAdapter(ctx, images)
                imgUrl = images[0]
            }
            url = item.url
            title = item.desc
            publish = item.publishedAt
            author = item.who
        }
        vh.titleTv.setOnClickListener {
            val intent = Intent(ctx, ArticleActivity::class.java)
            intent.putExtra("url", url)
            intent.putExtra("title", title)
            intent.putExtra("publish", publish)
            intent.putExtra("author", author)
            intent.putExtra("imgUrl", imgUrl)
            intent.putExtra("isArticle",true)
            ctx.startActivity(intent)
        }
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.title_tv)
        val imgRv: RecyclerView = itemView.findViewById(R.id.img_rv)
    }

}