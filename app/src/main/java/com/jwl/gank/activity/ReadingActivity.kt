package com.jwl.gank.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.jwl.gank.R
import com.jwl.gank.adapter.ReadPagerAdapter
import com.jwl.gank.bean.xiandu.subcategory.SubCategoryBean
import com.jwl.gank.frag.XianDuFragment
import com.jwl.gank.net.RetrofitClient
import com.jwl.gank.service.DataService
import kotlinx.android.synthetic.main.activity_reading.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReadingActivity : AppCompatActivity() {

    lateinit var readPagerAdapter: ReadPagerAdapter
    lateinit var titles: MutableList<String>
    lateinit var fragments: MutableList<Fragment>
    var subCategory: String = "wow"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reading)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        getDatas(subCategory)
        initEvent()

    }

    private fun initEvent() {
        reading_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}
            override fun onPageSelected(position: Int) {
                toolbar.title = readPagerAdapter.titles[position]
            }

        })
    }

    private fun getDatas(subCategory: String) {
        this.subCategory = subCategory
        initView()
        getCategory(subCategory)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.xiandu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var subCategory = ""
        progress.visibility = View.VISIBLE
        when (item!!.itemId) {
            R.id.wow -> {
                subCategory = "wow"
            }
            R.id.apps -> {
                subCategory = "apps"
            }
            R.id.imrich -> {
                subCategory = "imrich"
            }
            R.id.funny -> {
                subCategory = "funny"
            }
            R.id.android -> {
                subCategory = "android"
            }
            R.id.diediedie -> {
                subCategory = "diediedie"
            }
            R.id.thinking -> {
                subCategory = "thinking"
            }
            R.id.ios -> {
                subCategory = "iOS"
            }
            R.id.teamblog -> {
                subCategory = "teamblog"
            }
            else -> {
                subCategory = "wow"
            }

        }
        if (this.subCategory == subCategory) {
            progress.visibility = View.GONE
            return true
        } else {
            getDatas(subCategory)
        }
        return true
    }


    private fun initView() {
        fragments = mutableListOf()
        titles = mutableListOf()
        readPagerAdapter = ReadPagerAdapter(fragments, titles, supportFragmentManager)
        reading_vp.adapter = readPagerAdapter
        reading_tl.setupWithViewPager(reading_vp)

    }

    fun getCategory(subCategory: String) {
        RetrofitClient.newInstance(this, DataService::class.java)
                .getSubCategory(subCategory)
                .enqueue(object : Callback<SubCategoryBean> {
                    override fun onFailure(call: Call<SubCategoryBean>, t: Throwable) {}

                    override fun onResponse(call: Call<SubCategoryBean>, response: Response<SubCategoryBean>) {
                        val subCategoryBean = response.body()
                        if (subCategoryBean!!.error == false) {
                            val results = subCategoryBean.results
                            if (results != null) {
                                for (result in results) {
                                    fragments.add(XianDuFragment.newInstance(result.id))
                                    titles.add(result.title)
                                }
                                progress.visibility = View.GONE
                                readPagerAdapter.notifyDataSetChanged()
                                toolbar.title = readPagerAdapter.getPageTitle(0)
                            }
                        }
                    }

                })

    }
}
