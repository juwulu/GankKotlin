package com.jwl.gank.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.jwl.gank.R
import com.jwl.gank.adapter.FavoriteAdapter
import com.jwl.gank.room.AppDatabaseHelper
import com.jwl.gank.room.read.Read
import kotlinx.android.synthetic.main.activity_read_record.*

class ReadRecordActivity : AppCompatActivity() {

    lateinit var reads: MutableList<Read>
    var pageSize = 5
    var pageNum = 1
    lateinit var readAdapter: FavoriteAdapter<Read>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_record)
        toolbar.setNavigationOnClickListener { finish() }
        reads = mutableListOf()
        readAdapter = FavoriteAdapter(this, reads)
        read_record_rv.layoutManager = LinearLayoutManager(this)
        read_record_rv.adapter = readAdapter

        getFavorites()
        initEvent()


    }

    private fun initEvent() {
        read_record_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = read_record_rv.layoutManager as LinearLayoutManager
                if (linearLayoutManager.findLastVisibleItemPosition() >= reads.size - 1) {
                    pageNum++
                    progress.visibility = View.VISIBLE
                    getFavorites()
                }
            }
        })
    }

    private fun getFavorites() {
        Thread {
            reads.addAll(AppDatabaseHelper.getInstance(this@ReadRecordActivity).getReads(pageNum, pageSize))
            runOnUiThread {
                readAdapter.notifyDataSetChanged()
                progress.visibility = View.GONE
            }
            Log.d("aaa", "${reads.size}")
        }.start()
    }
}
