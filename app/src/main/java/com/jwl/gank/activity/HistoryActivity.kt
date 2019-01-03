package com.jwl.gank.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jwl.gank.R
import com.jwl.gank.adapter.HistoryDateAdapter
import com.jwl.gank.bean.HistoryDateBean
import com.jwl.gank.net.RetrofitClient
import com.jwl.gank.service.DataService
import kotlinx.android.synthetic.main.activity_history.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        toolbar.setNavigationOnClickListener { finish() }
        getHistoryDate()
    }

    private fun getHistoryDate() {
        RetrofitClient.newInstance(applicationContext, DataService::class.java)
                .getHistoryDates()
                .enqueue(object : Callback<HistoryDateBean> {
                    override fun onFailure(call: Call<HistoryDateBean>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<HistoryDateBean>, response: Response<HistoryDateBean>) {
                        val historyDateBean = response.body()
                        if (historyDateBean!!.error == false) {
                            val results = historyDateBean.results
                            history_date_rv.layoutManager = LinearLayoutManager(applicationContext)
                            history_date_rv.adapter = HistoryDateAdapter(applicationContext, results!!)
                            progress.visibility= View.GONE
                        }
                    }

                })
    }
}
