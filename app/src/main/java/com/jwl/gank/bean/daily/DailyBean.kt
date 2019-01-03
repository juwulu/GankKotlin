package com.jwl.gank.bean.daily


import com.google.gson.annotations.SerializedName

data class ResultItem(@SerializedName("publishedAt")
                       val publishedAt: String = "",
                       @SerializedName("_id")
                       val Id: String = "",
                       @SerializedName("title")
                       val title: String = "",
                       @SerializedName("content")
                       val content: String = "")


data class DailyBean(@SerializedName("error")
                     val error: Boolean = false,
                     @SerializedName("results")
                     val results: List<ResultItem>?)


