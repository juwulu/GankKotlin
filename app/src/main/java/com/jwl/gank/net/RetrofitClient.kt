package com.jwl.gank.net

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Environment
import com.jwl.gank.App
import com.jwl.gank.Config
import com.shuyu.gsyvideoplayer.utils.NetworkUtils
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import android.net.NetworkInfo



public class RetrofitClient {



    companion object {
        fun isNetworkAvalible(context: Context): Boolean {
            // 获得网络状态管理器
            val connectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (connectivityManager == null) {
                return false
            } else {
                // 建立网络数组
                val net_info = connectivityManager.allNetworkInfo

                if (net_info != null) {
                    for (i in net_info.indices) {
                        // 判断获得的网络状态是否是处于连接状态
                        if (net_info[i].state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                }
            }
            return false
        }

        fun <T> newInstance(context:Context,clazz: Class<T>): T {
            /**
             * 有网时候的缓存
             */
            val netCacheInterceptor: Interceptor = (object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                    val response = chain.proceed(request)
                    return response.newBuilder()
                            .header("Cache-Control", "public, max-age=30")
                            .removeHeader("Pragma")
                            .build()

                }

            })
            /**
             * 没有网时候的缓存
             */
            val offineCacheInterceptor = (object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request = chain.request()
                    if(!isNetworkAvalible(context)){
                        request = request.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=3600000000")
                                .build()
                    }
                    return chain.proceed(request)
                }

            })
            val file = File(Environment.getExternalStorageDirectory(), ".gankCache")
            val cacheSize: Long = 10L * 1024L * 1024L
            val cache = Cache(file, cacheSize)
            val client = OkHttpClient.Builder()
                    .addNetworkInterceptor(netCacheInterceptor)
                    .addInterceptor(offineCacheInterceptor)
                    .cache(cache)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(clazz)

        }
    }


}