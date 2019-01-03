package com.jwl.gank.frag

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jwl.gank.Config
import com.jwl.gank.R
import com.jwl.gank.adapter.ArticleAdapter
import com.jwl.gank.bean.ArticleBean
import com.jwl.gank.bean.Result
import com.jwl.gank.service.DataService
import com.jwl.gank.net.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleFragment : Fragment() {

    var results = mutableListOf<Result>()
    var adapter: ArticleAdapter? = null
    var category:String ="Android"
    var pageNum:Int = 1

    companion object {
        fun newInstance(category: String): ArticleFragment {
            var fragment = ArticleFragment();
            var bundle = Bundle();
            bundle.putString("category", category)
            fragment.setArguments(bundle)
            return fragment;
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_article, null, false)
        val articleRv: RecyclerView = view.findViewById(R.id.article_rv)
        articleRv.layoutManager = LinearLayoutManager(activity)
        adapter = ArticleAdapter(this!!.activity!!, results)
        articleRv.adapter = adapter
        initEvent(articleRv)
        return view
    }

    private fun initEvent(articleRv: RecyclerView) {
        articleRv.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = articleRv.layoutManager as LinearLayoutManager
                if (linearLayoutManager.findLastVisibleItemPosition()==results.size-1) {
                    getArticles(category,"${pageNum++}")
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        category = arguments?.getString("category")!!
        getArticles(category!!,"$pageNum")
    }

    fun getArticles(category:String,pageNum:String){
        RetrofitClient
                .newInstance(activity!!.applicationContext,DataService::class.java)
                .getDatas(category!!, Config.PAGE_SIZE, pageNum).enqueue(object : Callback<ArticleBean> {
                    override fun onFailure(call: Call<ArticleBean>?, t: Throwable?) {
                        Toast.makeText(this@ArticleFragment.context, t.toString(), Toast.LENGTH_LONG)
                    }

                    override fun onResponse(call: Call<ArticleBean>?, response: Response<ArticleBean>?) {
                        val articleBean = response!!.body()
                        if (articleBean != null) {
                            results.addAll(articleBean.results)
                        }
                        adapter!!.notifyDataSetChanged()
                    }

                })
    }

}