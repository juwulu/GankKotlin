package com.jwl.gank.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.jwl.gank.R
import com.jwl.gank.adapter.RestMovieAdapter
import com.jwl.gank.bean.RestMovieBean
import com.jwl.gank.bean.ResultsItem
import com.jwl.gank.net.RetrofitClient
import com.jwl.gank.service.DataService
import kotlinx.android.synthetic.main.activity_rest_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestMovieActivity : AppCompatActivity() {
    var pageNum = 1

    lateinit var results: MutableList<ResultsItem>
    lateinit var restMovieAdapter: RestMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_movie)
        toolbar.setNavigationOnClickListener { finish() }

        results = mutableListOf()
        rest_rv.layoutManager = LinearLayoutManager(this)
        restMovieAdapter = RestMovieAdapter(this, results)
        rest_rv.adapter = restMovieAdapter

        getRestMovies(15, pageNum)

        rest_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = rest_rv.layoutManager as LinearLayoutManager
                if (linearLayoutManager.findLastVisibleItemPosition() >= results.size - 1) {
                    pageNum++
                    getRestMovies(15, pageNum)
                }
            }
        })

    }

    private fun getRestMovies(pageSize: Int, pageNum: Int) {
        RetrofitClient.newInstance(DataService::class.java)
                .getRestMovies("$pageSize", "$pageNum").enqueue(object : Callback<RestMovieBean> {
                    override fun onResponse(call: Call<RestMovieBean>?, response: Response<RestMovieBean>?) {
                        val restMovieBean: RestMovieBean = response!!.body()!!
                        results.addAll(restMovieBean.results!!)
                        restMovieAdapter.notifyDataSetChanged()
                    }

                    override fun onFailure(call: Call<RestMovieBean>?, t: Throwable?) {
                        Toast.makeText(this@RestMovieActivity, t!!.message, Toast.LENGTH_LONG)
                    }

                })

    }


}
