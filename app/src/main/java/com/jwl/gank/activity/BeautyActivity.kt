package com.jwl.gank.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import com.jwl.gank.Config
import com.jwl.gank.R
import com.jwl.gank.adapter.BeautyAdapter
import com.jwl.gank.bean.ArticleBean
import com.jwl.gank.bean.Result
import com.jwl.gank.net.RetrofitClient
import com.jwl.gank.service.ArticleService
import kotlinx.android.synthetic.main.activity_beauty.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeautyActivity : AppCompatActivity() {
    lateinit var results: MutableList<Result>
    lateinit var beautyAdapter: BeautyAdapter
    var pageNum: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beauty)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        results = mutableListOf()
        beautyAdapter = BeautyAdapter(this, results)
        beauty_rv.layoutManager = LinearLayoutManager(this)
        beauty_rv.adapter = beautyAdapter
        getBeauties("福利", "$pageNum")
        setToolBarMargin()
        toolbar.setNavigationOnClickListener { finish() }

        beauty_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = beauty_rv.layoutManager as LinearLayoutManager
                if (linearLayoutManager.findLastVisibleItemPosition() >= results.size - 1) {
                    pageNum++
                    getBeauties("福利", "$pageNum")
                    Log.d("visible", "${toolbar.visibility}")
                }
            }
        })

    }

    private fun setToolBarMargin() {
        val layoutParams: AppBarLayout.LayoutParams = toolbar.layoutParams as AppBarLayout.LayoutParams
        var statusBarHeight: Int = 0
        var resourceId: Int = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = this.getResources().getDimensionPixelSize(resourceId);
        }
        layoutParams.setMargins(0, statusBarHeight, 0, 0)
        toolbar.setLayoutParams(layoutParams)
    }


    fun getBeauties(category: String, pageNum: String) {
        RetrofitClient
                .newInstance(ArticleService::class.java)
                .getArticles(category!!, Config.PAGE_SIZE, pageNum).enqueue(object : Callback<ArticleBean> {
                    override fun onFailure(call: Call<ArticleBean>?, t: Throwable?) {
                        Toast.makeText(this@BeautyActivity, t.toString(), Toast.LENGTH_LONG)
                    }

                    override fun onResponse(call: Call<ArticleBean>?, response: Response<ArticleBean>?) {
                        val articleBean = response!!.body()
                        results.addAll(articleBean!!.results)
                        beautyAdapter!!.notifyDataSetChanged()
                    }
                })
    }
}
