package com.jwl.gank.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jwl.gank.R
import kotlinx.android.synthetic.main.activity_article_detail.*

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

        article_wb.loadUrl(url)

    }
}
