package com.jwl.gank.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jwl.gank.R
import com.jwl.gank.adapter.TodayAdapter
import com.jwl.gank.bean.today.*
import com.jwl.gank.net.RetrofitClient
import com.jwl.gank.service.DataService
import kotlinx.android.synthetic.main.activity_today.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodayActivity : AppCompatActivity() {
    lateinit var android: MutableList<AndroidItem>
    lateinit var app: MutableList<AppItem>
    lateinit var iOS: MutableList<IOSItem>
    lateinit var 休息视频: MutableList<休息视频Item>
    lateinit var 前端: MutableList<前端Item>
    lateinit var 拓展资源: MutableList<拓展资源Item>
    lateinit var 瞎推荐: MutableList<瞎推荐Item>
    lateinit var 福利: MutableList<福利Item>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today)
        setImmerseLayout(toolbar)
        android = mutableListOf()
        app = mutableListOf()
        iOS = mutableListOf()
        休息视频 = mutableListOf()
        前端 = mutableListOf()
        拓展资源 = mutableListOf()
        瞎推荐 = mutableListOf()
        福利 = mutableListOf()

        toolbar.setNavigationOnClickListener { finish() }
        getTodayDatas()
    }

    private fun getTodayDatas() {
        val todayDatas = RetrofitClient.newInstance(applicationContext, DataService::class.java).getTodayDatas()
        todayDatas.enqueue(object : Callback<TodayBean> {
            override fun onFailure(call: Call<TodayBean>, t: Throwable) {}

            override fun onResponse(call: Call<TodayBean>, response: Response<TodayBean>) {
                val todayBean = response.body()
                if (todayBean!!.error == false) {
                    var results = todayBean.results
                    if (results.android != null) {
                        android.addAll(results.android!!)
                    }
                    if (results.app != null) {
                        app.addAll(results.app!!)
                    }
                    if (results.iOS != null) {
                        iOS.addAll(results.iOS!!)
                    }
                    if (results.休息视频 != null) {
                        休息视频.addAll(results.休息视频!!)
                    }
                    if (results.前端 != null) {
                        前端.addAll(results.前端!!)
                    }
                    if (results.拓展资源 != null) {
                        拓展资源.addAll(results.拓展资源!!)
                    }
                    if (results.瞎推荐 != null) {
                        瞎推荐.addAll(results.瞎推荐!!)
                    }
                    if (results.福利 != null) {
                        福利.addAll(results.福利!!)
                    }
                    var size = todayBean.category!!.size
                    runOnUiThread {
                        Glide.with(this@TodayActivity).load(福利!![0].url).apply(RequestOptions().placeholder(R.drawable.placeholder)).into(header_iv)
                        var todayAdapter = TodayAdapter(this@TodayActivity, size, android, app, iOS, 休息视频, 前端, 拓展资源, 瞎推荐)
                        today_rv.layoutManager = LinearLayoutManager(this@TodayActivity)
                        today_rv.adapter = todayAdapter
                    }


                }
            }

        })
    }

    fun setImmerseLayout(view: View) {// view为标题栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            var statusBarHeight = getStatusBarHeight()
            view.setPadding(0, statusBarHeight, 0, 0)
        }
    }

    fun getStatusBarHeight(): Int {
        var result = 0
        var resourceId = getResources().getIdentifier("status_bar_height", "dimen",
                "android")
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId)
        }
        return result
    }

}
