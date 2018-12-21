package com.jwl.gank.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.jwl.gank.R
import com.jwl.gank.adapter.FavoriteAdapter
import com.jwl.gank.room.AppDatabaseHelper
import com.jwl.gank.room.favorite.Favorite
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {


    lateinit var favorites: MutableList<Favorite>
    var pageSize = 5
    var pageNum = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        toolbar.setNavigationOnClickListener { finish() }
        favorites = mutableListOf()

        favorite_rv.layoutManager = LinearLayoutManager(this)
        favorite_rv.adapter = FavoriteAdapter(this, favorites)

        getFavorites()
        initEvent()


    }

    private fun initEvent() {
        favorite_rv.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = favorite_rv.layoutManager as LinearLayoutManager
                if (linearLayoutManager.findLastVisibleItemPosition() >= favorites.size - 1) {
                    pageNum++
                    getFavorites()
                }
            }
        })
    }

    private fun getFavorites() {
        Thread {
            favorites.addAll(AppDatabaseHelper.getInstance(this@FavoriteActivity).getFavorites(pageNum,pageSize))
            runOnUiThread {
                (favorite_rv.adapter as FavoriteAdapter).notifyDataSetChanged()
            }
            Log.d("aaa","${favorites.size}")
        }.start()
    }
}
