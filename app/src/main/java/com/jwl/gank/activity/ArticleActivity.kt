package com.jwl.gank.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.Toast
import com.jwl.gank.R
import com.jwl.gank.room.AppDatabaseHelper
import com.jwl.gank.room.favorite.Favorite
import com.jwl.gank.widget.ScrollWebView
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        val publish = intent.getStringExtra("publish")
        val author = intent.getStringExtra("author")
        val imgUrl = intent.getStringExtra("imgUrl")
        val isArticle = intent.getBooleanExtra("isArticle", false)


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
        article_wb.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                article_pb.visibility=View.GONE
            }
        })

        Thread({
            val favorite = AppDatabaseHelper.getInstance(this).queryFavoriteByTitle(title)
            if (favorite!=null) {
                runOnUiThread(Runnable {
                    article_fb.isSelected = true
                })
            }
        }).start()




        article_wb.setWebChromeClient(WebChromeClient());//这行最好不要丢掉

        article_wb.loadUrl(url)

        article_wb.setOnScrollChangeListener(object : ScrollWebView.OnScollChangeListener {
            @SuppressLint("RestrictedApi")
            override fun onTurnDown() {
                if (isArticle) {
                    article_fb.visibility = View.VISIBLE
                }
                article_pb.visibility=View.GONE
            }

            @SuppressLint("RestrictedApi")
            override fun onTurnUp() {
                if (isArticle) {
                    article_fb.visibility = View.GONE
                }
                article_pb.visibility=View.GONE
            }

        })

        if (isArticle) {
            article_fb.visibility = View.VISIBLE
        } else {
            article_fb.visibility = View.GONE
        }



        article_fb.setOnClickListener {
            article_fb.isSelected = !article_fb.isSelected
            Thread(Runnable {
                if (article_fb.isSelected) {
                    AppDatabaseHelper.getInstance(this@ArticleActivity).insertFavorite(Favorite(0, title, publish, author, imgUrl, url))
                    runOnUiThread(Runnable { Toast.makeText(this@ArticleActivity, "收藏成功", Toast.LENGTH_LONG).show() })
                } else {
                    AppDatabaseHelper.getInstance(this@ArticleActivity).deleteFavorite(title)
                    runOnUiThread(Runnable { Toast.makeText(this@ArticleActivity, "取消收藏成功", Toast.LENGTH_LONG).show() })
                }
            }).start()

        }


    }
}
