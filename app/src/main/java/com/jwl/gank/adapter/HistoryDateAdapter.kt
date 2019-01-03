package com.jwl.gank.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jwl.gank.R
import com.jwl.gank.activity.DailyActivity

/**
 * author:  lujunwu
 * date:    2019/1/3 16:28
 * desc:    NoDiscription
 */
public class HistoryDateAdapter(var ctx: Context,var  strings:List<String>) : RecyclerView.Adapter<HistoryDateAdapter.VH>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HistoryDateAdapter.VH {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_text_layout, null, false)
        return VH(v)
    }

    override fun getItemCount(): Int {
        return strings.size
    }

    override fun onBindViewHolder(vh: HistoryDateAdapter.VH, position: Int) {
        vh.dateTv.setText(strings[position])
        vh.dateTv.setOnClickListener {
            val intent = Intent(ctx, DailyActivity::class.java)
            intent.putExtra("date",strings[position].replace("-","/"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ctx.startActivity(intent)
        }
    }

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        var dateTv: TextView = v.findViewById(R.id.date_tv)
    }

}