package com.jwl.gank.frag

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jwl.gank.R
import com.jwl.gank.adapter.ArticleAdapter
import com.jwl.gank.bean.ArticleBean
import com.jwl.gank.bean.Result
import com.jwl.gank.service.ArticleService
import com.jwl.gank.utils.RetrofitUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class ArticleFragment: Fragment() {

    var results = mutableListOf<Result>()
    var adapter: ArticleAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_article, null, false)
        val articleRv:RecyclerView = view.findViewById(R.id.article_rv)
        articleRv.layoutManager=LinearLayoutManager(activity)
        adapter=ArticleAdapter(this!!.activity!!,results)
        articleRv.adapter=adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val articleService = RetrofitUtils<ArticleService>().newInstance(ArticleService::class.java)
        articleService.getArticles("android","10","1").enqueue(object:Callback<ArticleBean>{
            override fun onFailure(call: Call<ArticleBean>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<ArticleBean>?, response: Response<ArticleBean>?) {
                val articleBean = response!!.body()
                results.addAll(articleBean!!.results)
                adapter!!.notifyDataSetChanged()
            }

        })

    }

}