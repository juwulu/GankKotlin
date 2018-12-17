package com.jwl.gank.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Toast
import com.jwl.gank.R
import com.jwl.gank.widget.ScrollWebView
import kotlinx.android.synthetic.main.activity_article_detail.*
import kotlinx.android.synthetic.main.activity_article_detail.view.*

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")

        title_tv.setText(title)

        val webSettings = article_wb.settings
        webSettings.setAppCacheEnabled(true)
        webSettings.setDomStorageEnabled(true)
        webSettings.supportMultipleWindows()
        webSettings.setAllowContentAccess(true)
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS)
        webSettings.setUseWideViewPort(true)
        webSettings.setLoadWithOverviewMode(true)
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true)
        webSettings.setLoadsImagesAutomatically(true)
        article_wb.setWebViewClient(object :WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        })

        article_wb.setWebChromeClient(WebChromeClient());//这行最好不要丢掉

        article_wb.loadUrl(url)

        article_wb.setOnScrollChangeListener(object :ScrollWebView.OnScollChangeListener{
            @SuppressLint("RestrictedApi")
            override fun onTurnDown() {
                article_fb.visibility = View.VISIBLE
            }

            @SuppressLint("RestrictedApi")
            override fun onTurnUp() {
                article_fb.visibility= View.GONE
            }

        })


        article_fb.setOnClickListener {
            article_fb.isSelected=!article_fb.isSelected
            if (article_fb.isSelected){
                Toast.makeText(this@ArticleActivity,"收藏成功",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@ArticleActivity,"取消收藏成功",Toast.LENGTH_LONG).show()
            }
        }


    }
}
