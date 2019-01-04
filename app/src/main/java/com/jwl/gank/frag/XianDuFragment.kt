package com.jwl.gank.frag

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jwl.gank.R
import com.jwl.gank.adapter.ReadAdapter
import com.jwl.gank.bean.xiandu.article.ResultsItem
import com.jwl.gank.bean.xiandu.article.XianDuBean
import com.jwl.gank.net.RetrofitClient
import com.jwl.gank.service.DataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class XianDuFragment : Fragment() {

    var results = mutableListOf<ResultsItem>()
    var adapter: ReadAdapter? = null
    var id: String = "qdaily"
    var pageNum: Int = 1

    companion object {
        fun newInstance(id: String): XianDuFragment {
            var fragment = XianDuFragment()
            var bundle = Bundle();
            bundle.putString("id", id)
            fragment.setArguments(bundle)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_article, null, false)
        val articleRv: RecyclerView = view.findViewById(R.id.article_rv)
        articleRv.layoutManager = LinearLayoutManager(activity)
        adapter = ReadAdapter(this!!.activity!!, results)
        articleRv.adapter = adapter
        initEvent(articleRv)
        return view
    }

    private fun initEvent(articleRv: RecyclerView) {
        articleRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = articleRv.layoutManager as LinearLayoutManager
                if (linearLayoutManager.findLastVisibleItemPosition() == results.size - 1) {
                    getReads(id, "${pageNum++}")
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        id = arguments?.getString("id")!!
        getReads(id!!, "$pageNum")
    }

    fun getReads(id: String, pageNum: String) {
        RetrofitClient.newInstance(activity!!.applicationContext, DataService::class.java)
                .getXianDu(id, pageNum, "10")
                .enqueue(object : Callback<XianDuBean> {
                    override fun onFailure(call: Call<XianDuBean>, t: Throwable) {}

                    override fun onResponse(call: Call<XianDuBean>, response: Response<XianDuBean>) {
                        val xianDuBean = response.body()
                        if (xianDuBean != null) {
                            if (xianDuBean.error == false) {
                                results.addAll(xianDuBean.results!!)
                            }
                            adapter!!.notifyDataSetChanged()
                        }
                    }

                })

    }

}