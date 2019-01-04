package com.jwl.gank.bean.xiandu.subcategory


import com.google.gson.annotations.SerializedName

data class ResultsItem(@SerializedName("icon")
                       val icon: String = "",
                       @SerializedName("created_at")
                       val createdAt: String = "",
                       @SerializedName("_id")
                       val outId: String = "",
                       @SerializedName("id")
                       val id: String = "",
                       @SerializedName("title")
                       val title: String = "")


data class SubCategoryBean(@SerializedName("error")
                           val error: Boolean = false,
                           @SerializedName("results")
                           val results: List<ResultsItem>?)


