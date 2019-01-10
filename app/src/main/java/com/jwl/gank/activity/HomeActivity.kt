package com.jwl.gank.activity

import android.Manifest
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.jwl.gank.R
import com.jwl.gank.adapter.HomeAdapter
import com.jwl.gank.frag.ArticleFragment
import com.jwl.gank.utils.DownloadListener
import com.jwl.gank.utils.DownloadUtil
import com.jwl.gank.utils.InstallUtil
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import java.io.File


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val categories: Array<String> = arrayOf("Android", "iOS", "前端", "App", "拓展资源")
    lateinit var dialog: ProgressDialog
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        }
    }

    private fun initEvent() {
        pic_iv.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@HomeActivity, BeautyActivity::class.java))
            }
        })
        movie_iv.setOnClickListener { startActivity(Intent(this@HomeActivity, RestMovieActivity::class.java)) }
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
            R.id.action_settings -> {
                startActivity(Intent(this@HomeActivity, SearchActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.favorite -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }
            R.id.nav_newest -> {
                startActivity(Intent(this@HomeActivity, TodayActivity::class.java))
            }
            R.id.nav_history -> {
                startActivity(Intent(this@HomeActivity, HistoryActivity::class.java))
            }
            R.id.nav_read_history -> {
                startActivity(Intent(this, ReadRecordActivity::class.java))
            }
            R.id.nav_read -> {
                startActivity(Intent(this, ReadingActivity::class.java))
            }
            R.id.nav_feedback -> {
                val uri = Uri.parse("https:// github.com/juwulu/GankKotlin/issues")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            R.id.nav_update -> {
                dialog = ProgressDialog(this)
                dialog.setTitle("App更新")
                dialog.setIcon(R.drawable.avatar)
                dialog.setMessage("Gank更新中...")
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                dialog.show()
                updateCheck()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun updateCheck() {
        DownloadUtil.downloadApk("http://imtt.dd.qq.com/16891/164BD05ED3651EF1474FCA59F20EA7C3.apk", object : DownloadListener {
            override fun onProgress(progress: Int) {
                dialog.progress = progress
            }

            override fun onFinish() {
                dialog.dismiss()
                runOnUiThread(Runnable {
                    InstallUtil(this@HomeActivity,"${Environment.getExternalStorageDirectory()}/gank/gank.apk").install()
                })
            }

            override fun onFaild() {
                dialog.dismiss()
                Toast.makeText(applicationContext, "访问服务器失败", Toast.LENGTH_LONG).show()
            }

        })


    }

      fun installNormal(context: Context,apkPath:String) {
        var intent =  Intent(Intent.ACTION_VIEW)
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            val file = (File(apkPath))
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            val apkUri = FileProvider.getUriForFile(context, "com.jwl.gank", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile( File(apkPath)),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 111) {
            InstallUtil(this,"${Environment.getExternalStorageDirectory()}/gank/gank.apk").install()//再次执行安装流程，包含权限判等
        }
    }

}
