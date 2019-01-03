package com.jwl.gank.service

import com.jwl.gank.bean.ArticleBean
import com.jwl.gank.bean.HistoryDateBean
import com.jwl.gank.bean.RestMovieBean
import com.jwl.gank.bean.SearchBean
import com.jwl.gank.bean.daily.DailyBean
import com.jwl.gank.bean.today.TodayBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

public interface DataService{

    @GET("api/data/{category}/{pageSize}/{pageNum}")
    fun getDatas(@Path("category") category: String, @Path("pageSize") pageSize:String, @Path("pageNum") pageNum:String): Call<ArticleBean>

    @GET("api/data/休息视频/{pageSize}/{pageNum}")
    fun getRestMovies(@Path("pageSize") pageSize:String, @Path("pageNum") pageNum:String): Call<RestMovieBean>


    @GET("api/search/query/{keyword}/category/{category}/count/{pageSize}/page/{pageNum}")
    fun search(@Path("keyword") keyword:String,@Path("category") category: String,@Path("pageSize") pageSize: String,@Path("pageNum") pageNum: String):Call< SearchBean>

    @GET("api/today")
    fun getTodayDatas():Call<TodayBean>

    @GET("api/day/history")
    fun getHistoryDates():Call<HistoryDateBean>

    @GET("api/history/content/day/{date}")
    fun getDetailByDate(@Path("date") date:String):Call<DailyBean>
}