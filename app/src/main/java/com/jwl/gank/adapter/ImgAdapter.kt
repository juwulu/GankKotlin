package com.jwl.gank.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jwl.gank.R
import kotlinx.android.synthetic.main.activity_today.*

/**
 * author:  lujunwu
 * date:    2019/1/3 14:43
 * desc:    NoDiscription
 */
public class ImgAdapter(var ctx: Context, var strings:List<String>) : RecyclerView.Adapter<ImgAdapter.VH>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VH {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_img_layout, null, false)
        return VH(v)
    }

    override fun getItemCount(): Int {
        return strings.size
    }

    override fun onBindViewHolder(vh: VH, position: Int) {
        Glide.with(ctx).load(strings[position]).apply(RequestOptions().placeholder(R.drawable.placeholder)).into(vh.itemIv)
    }

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        val itemIv: ImageView = v.findViewById(R.id.item_iv)
    }

}