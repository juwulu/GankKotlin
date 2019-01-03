package com.jwl.gank.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jwl.gank.R
import com.jwl.gank.bean.today.*

/**
 * author:  lujunwu
 * date:    2019/1/3 10:10
 * desc:    NoDiscription
 */

public class TodayAdapter(var ctx: Context, var size: Int, var android: MutableList<AndroidItem>?, var app: MutableList<AppItem>, var iOS: MutableList<IOSItem>, var 休息视频: MutableList<休息视频Item>, var 前端: MutableList<前端Item>, var 拓展资源: MutableList<拓展资源Item>, var 瞎推荐: MutableList<瞎推荐Item>) : RecyclerView.Adapter<TodayAdapter.VH>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VH {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_today_layout, null, false)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(vh: VH, position: Int) {
        when (position) {
            0 -> {
                if (android!!.size==0) {
                    vh.categoryTv.visibility=View.GONE
                }
                vh.categoryTv.setText("Android")
                vh.categoryRv.layoutManager = LinearLayoutManager(ctx)
                Log.d("aaa", "${android!!.size}")
                vh.categoryRv.adapter = TodayDetailAdapter(ctx, this!!.android!!)
            }
            1 -> {
                if (iOS.size==0) {
                    vh.categoryTv.visibility=View.GONE
                }
                vh.categoryTv.setText("iOS")
                vh.categoryRv.layoutManager = LinearLayoutManager(ctx)
                Log.d("aaa", "${iOS!!.size}")
                vh.categoryRv.adapter = TodayDetailAdapter(ctx, this!!.iOS!!)
            }
            2 -> {
                if (前端!!.size==0) {
                    vh.categoryTv.visibility=View.GONE
                }
                vh.categoryTv.setText("前端")
                vh.categoryRv.layoutManager = LinearLayoutManager(ctx)
                Log.d("aaa", "${前端!!.size}")
                vh.categoryRv.adapter = TodayDetailAdapter(ctx, this!!.前端!!)
            }
            3 -> {
                if (app!!.size==0) {
                    vh.categoryTv.visibility=View.GONE
                }
                vh.categoryTv.setText("App")
                vh.categoryRv.layoutManager = LinearLayoutManager(ctx)
                Log.d("aaa", "${app!!.size}")
                vh.categoryRv.adapter = TodayDetailAdapter(ctx, this!!.app!!)
            }
            4 -> {
                if (拓展资源!!.size==0) {
                    vh.categoryTv.visibility=View.GONE
                }
                vh.categoryTv.setText("拓展资源")
                vh.categoryRv.layoutManager = LinearLayoutManager(ctx)
                Log.d("aaa", "${拓展资源.size}")
                vh.categoryRv.adapter = TodayDetailAdapter(ctx, this!!.拓展资源!!)
            }
            5 -> {
                if (瞎推荐!!.size==0) {
                    vh.categoryTv.visibility=View.GONE
                }
                vh.categoryTv.setText("瞎推荐")
                vh.categoryRv.layoutManager = LinearLayoutManager(ctx)
                Log.d("aaa", "${瞎推荐!!.size}")
                vh.categoryRv.adapter = TodayDetailAdapter(ctx, this!!.瞎推荐!!)
            }
            6 -> {
                if (休息视频!!.size==0) {
                    vh.categoryTv.visibility=View.GONE
                }
                vh.categoryTv.setText("休息视频")
                vh.categoryRv.layoutManager = LinearLayoutManager(ctx)
                Log.d("aaa", "${休息视频!!.size}")
                vh.categoryRv.adapter = TodayDetailAdapter(ctx, this!!.休息视频!!)
            }
            else -> {
            }
        }
    }

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        var categoryTv: TextView = v.findViewById(R.id.category_tv)
        var categoryRv: RecyclerView = v.findViewById(R.id.category_rv)
    }

}