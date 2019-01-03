package com.jwl.gank.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.jwl.gank.R
import com.jwl.gank.adapter.HomeAdapter
import com.jwl.gank.frag.ArticleFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val categories: Array<String> = arrayOf("Android", "iOS", "前端", "App", "拓展资源")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "暂时不能发布文章", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        initTabLayout(categories)
        initEvent()
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),100)
        }
    }

    private fun initEvent() {
        pic_iv.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@HomeActivity,BeautyActivity::class.java))
            }
        })
        movie_iv.setOnClickListener { startActivity(Intent(this@HomeActivity,RestMovieActivity::class.java)) }
    }


    private fun initTabLayout(titles: Array<String>) {
        var fragments: ArrayList<Fragment> = arrayListOf()
        for (title in titles) {
            fragments.add(ArticleFragment.newInstance(title))
        }

        home_vp.adapter = HomeAdapter(supportFragmentManager, fragments)
        tablayout.setupWithViewPager(home_vp)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings->{
                startActivity(Intent(this@HomeActivity,SearchActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.favorite -> {
                startActivity(Intent(this,FavoriteActivity::class.java))
            }
            R.id.nav_newest->{
                startActivity(Intent(this@HomeActivity,TodayActivity::class.java))
            }
            R.id.nav_history->{
                startActivity(Intent(this@HomeActivity,HistoryActivity::class.java))
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
