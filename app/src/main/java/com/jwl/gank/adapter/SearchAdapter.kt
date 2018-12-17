package com.jwl.gank.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jwl.gank.R
import com.jwl.gank.bean.SearchResultItem
import java.util.*

/**
 * author:  lujunwu
 * date:    2018/12/17 12:36
 * desc:    NoDiscription
 */
class SearchAdapter(var ctx:Context,var searchs:MutableList<SearchResultItem>): RecyclerView.Adapter<SearchAdapter.VH>() {

    interface OnItemClickListener{
        fun onItemClick(v:View,position:Int)
    }

    private lateinit var listener:SearchAdapter.OnItemClickListener


    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        listener = onItemClickListener
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VH {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_search_layout, null, false)
        return VH(view)
    }

    override fun onBindViewHolder(p0: VH, p1: Int) {
        val searchResultItem = searchs[p1]
        p0.titleTv.text = searchResultItem.desc
        p0.categoryTv.text = searchResultItem.type
        p0.authorTv.text = searchResultItem.who
        p0.timeTv.text = searchResultItem.publishedAt.substring(0,10)
        p0.itemRoot.setOnClickListener { listener.onItemClick(p0.itemRoot,p1)}
    }


    override fun getItemCount(): Int {
        return searchs.size
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTv:TextView = itemView.findViewById(R.id.title_tv)
        var categoryTv:TextView = itemView.findViewById(R.id.category_tv)
        var authorTv:TextView = itemView.findViewById(R.id.author_tv)
        var timeTv:TextView = itemView.findViewById(R.id.time_tv)
        var itemRoot:ConstraintLayout = itemView.findViewById(R.id.item_root)
    }








}





