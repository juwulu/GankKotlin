package com.jwl.gank.activity

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.jwl.gank.R
import com.jwl.gank.room.AppDatabaseHelper
import com.jwl.gank.room.search.Search
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    lateinit var searchs: MutableList<Search>

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = 0XFFFFFFFF.toInt()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR


        search_et.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    search()
                }
                return false
            }

        })



        val searchs = AppDatabaseHelper.getInstance(this).getAll()
        Log.d("ddd","$searchs.size")
        initEvent()

    }

    private fun search() {
        AppDatabaseHelper.getInstance(this).insertAll(Search(1,search_et.text.toString()))
    }

    private fun initEvent() {
        cancel_tv.setOnClickListener { finish() }

    }
}
