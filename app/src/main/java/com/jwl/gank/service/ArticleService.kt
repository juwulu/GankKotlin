package com.jwl.gank.service

import com.jwl.gank.bean.ArticleBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

public interface ArticleService{

    @GET("api/data/{category}/{pageSize}/{pageNum}")
    fun getArticles(@Path("category") category: String,@Path("pageSize") pageSize:String,@Path("pageNum") pageNum:String): Call<ArticleBean>
}