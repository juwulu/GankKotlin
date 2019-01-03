package com.jwl.gank.activity

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.chip.Chip
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.jwl.gank.R
import com.jwl.gank.R.id.*
import com.jwl.gank.adapter.SearchAdapter
import com.jwl.gank.bean.SearchBean
import com.jwl.gank.bean.SearchResultItem
import com.jwl.gank.net.RetrofitClient
import com.jwl.gank.room.AppDatabaseHelper
import com.jwl.gank.room.search.Search
import com.jwl.gank.service.DataService
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    lateinit var results: MutableList<SearchResultItem>
    lateinit var searchAdapter: SearchAdapter
    var pageNum = 1
    var pageSize = 20


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = 0XFFFFFFFF.toInt()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        query()
        search_et.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    if (search_et.text.toString().length == 0) {
                        Toast.makeText(this@SearchActivity, "请输入关键字", Toast.LENGTH_LONG)
                    } else {
                        insert()
                        search(search_et.text.toString().trim())
                    }
                }
                return false
            }

        })
        results = mutableListOf()
        result_rv.layoutManager = LinearLayoutManager(this@SearchActivity)
        searchAdapter = SearchAdapter(this@SearchActivity, results)
        result_rv.setAdapter(searchAdapter)

        initEvent()

    }

    private fun query() = Thread(Runnable {
        val searchs = AppDatabaseHelper.getInstance(this).getAll()
        runOnUiThread(Runnable {
            history_cg.removeAllViews()
            for (search in searchs) {
                val chip = Chip(this@SearchActivity)
                chip.chipText = search.record
                chip.textSize = 15f
                chip.isEnabled = true
                chip.setOnClickListener { search(chip.text.toString()) }
                history_cg.addView(chip)
            }
        })
    }).start()

    private fun search(keyword: String) {
        Log.d("keyword", keyword)
        history_cg.visibility = View.GONE
        history_ll.visibility=View.GONE
        result_rv.visibility = View.VISIBLE
        RetrofitClient.newInstance(this!!.applicationContext,DataService::class.java)
                .search(keyword, "all", "$pageSize", "$pageNum")
                .enqueue(object : Callback<SearchBean> {
                    override fun onFailure(call: Call<SearchBean>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<SearchBean>?, response: Response<SearchBean>?) {
                        val searchBean = response!!.body()
                        if (pageNum==1) {
                            results.clear()
                        }
                        results.addAll(searchBean!!.results!!)
                        Log.d("abc", "abc")
                        result_rv.adapter!!.notifyDataSetChanged()
                        searchAdapter.setOnItemClickListener(object : SearchAdapter.OnItemClickListener {
                            override fun onItemClick(v: View, position: Int) {
                                val intent = Intent(this@SearchActivity, ArticleActivity::class.java)
                                intent.putExtra("url", results[position].url)
                                intent.putExtra("title", results[position].desc)
                                startActivity(intent)
                            }

                        })


                    }

                })

    }


    private fun insert() {
        Thread(Runnable {
            AppDatabaseHelper.getInstance(this).insertAll(Search(0, search_et.text.toString()))
            query()
        }).start()
    }

    private fun initEvent() {
        cancel_tv.setOnClickListener { finish() }
        search_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (search_et.text.length == 0) {
                    result_rv.visibility = View.GONE
                    history_cg.visibility = View.VISIBLE
                    history_ll.visibility=View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        delete_tv.setOnClickListener {
            Thread(Runnable {
                AppDatabaseHelper.getInstance(this).deleteAll()
                runOnUiThread(Runnable {
                    history_cg.removeAllViews()

                })
            }).start()
        }
        result_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val linearLayoutManager = result_rv.layoutManager as LinearLayoutManager
                if (linearLayoutManager.findLastVisibleItemPosition()==results.size-1) {
                    pageNum++
                    search(search_et.text.toString().trim())
                }
            }
        })

    }
}
