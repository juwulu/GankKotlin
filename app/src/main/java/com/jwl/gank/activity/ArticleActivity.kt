package com.jwl.gank.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import com.jwl.gank.R
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

    }
}
