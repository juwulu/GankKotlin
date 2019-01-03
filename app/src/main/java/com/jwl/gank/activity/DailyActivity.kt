package com.jwl.gank.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jwl.gank.R
import com.jwl.gank.bean.daily.DailyBean
import com.jwl.gank.net.RetrofitClient
import com.jwl.gank.service.DataService
import kotlinx.android.synthetic.main.activity_daily.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
        val date = intent.getStringExtra("date")
        date_tv.setText(date)

        toolbar.setNavigationOnClickListener { finish()  }

        content_wb.getSettings().setJavaScriptEnabled(true)
        // 设置支持javascript
        content_wb.getSettings().setJavaScriptEnabled(true)
        // 启动缓存
        content_wb.getSettings().setAppCacheEnabled(true)
        // 设置缓存模式
        content_wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT)
        content_wb.webViewClient = (object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress.visibility = View.GONE
            }
        })



        getDetailByDate(date)
    }

    private fun getDetailByDate(date: String?) {
        RetrofitClient.newInstance(applicationContext, DataService::class.java)
                .getDetailByDate(date!!)
                .enqueue(object : Callback<DailyBean> {
                    override fun onFailure(call: Call<DailyBean>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<DailyBean>, response: Response<DailyBean>) {
                        val dailyBean = response.body()
                        if (dailyBean!!.error == false) {
                            val resultsItem = dailyBean.results!![0]
                            val content = resultsItem.content
                            val replace = content.replace("<img", "<img  width='100%' style=/\"word-wrap:break-word; font-family:Arial ")

                            content_wb.loadDataWithBaseURL(null,replace,"text/html","UTF-8",null)
                        }
                    }

                })
    }
}
