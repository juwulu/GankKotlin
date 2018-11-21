package com.jwl.gank.utils

import com.jwl.gank.Config
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

public class RetrofitUtils<T>{

    fun newInstance(clazz:Class<T>): T {
        val retrofit = Retrofit.Builder()
                .baseUrl(Config().BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(clazz)
    }
}