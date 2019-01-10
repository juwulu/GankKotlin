package com.jwl.gank.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import com.jwl.gank.net.RetrofitClient
import com.jwl.gank.service.DataService
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * author:  lujunwu
 * date:    2019/1/10 10:26
 * desc:    NoDiscription
 */

class DownloadUtil {
    companion object {
        fun downloadApk(url: String,downloadListener:DownloadListener) {
            val client = OkHttpClient.Builder().build()
            val request = Request.Builder().url(url).build()
            client.newCall(request).enqueue(object :okhttp3.Callback{
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    downloadListener.onFaild()
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    val body = response.body()
                    Log.d("ccc","${body!!.contentLength()}")

                    val contentLength = body!!.contentLength()
                    val inputStream = body.byteStream()
                    val dictionary = File("${Environment.getExternalStorageDirectory()}/gank")
                    if (!dictionary.exists()) {
                        dictionary.mkdir()
                    }
                    val file = File("${Environment.getExternalStorageDirectory()}/gank","gank.apk")
                    val outputStream = FileOutputStream(file)
                    val buffer = ByteArray(1024)
                    var length: Int = -1
                    var progress:Double = 0.0
                    while (inputStream.read(buffer).apply { length = this } != -1) {
                        outputStream.write(buffer,0,length)
                        Log.d("ddd","$length")
                        progress +=length
                        Log.d("eee","progress:$progress-------total:$contentLength")
                        val pProgress = (progress*100/contentLength)
                        if(progress<contentLength){
                            downloadListener.onProgress(pProgress.toInt())
                            Log.d("ccc","$pProgress")
                        }
                    }
                    inputStream.close()
                    outputStream.close()
                    Log.d("ccc","下载完成")
                    downloadListener.onFinish()
                }

            })

        }
    }
}

interface DownloadListener{
    fun onProgress(progress:Int)
    fun onFinish()
    fun onFaild()
}