package com.jwl.gank.bean.today

import com.google.gson.annotations.SerializedName

data class 拓展资源Item(@SerializedName("createdAt")
                    val createdAt: String = "",
                    @SerializedName("images")
                    val images: List<String>?,
                    @SerializedName("publishedAt")
                    val publishedAt: String = "",
                    @SerializedName("_id")
                    val Id: String = "",
                    @SerializedName("source")
                    val source: String = "",
                    @SerializedName("used")
                    val used: Boolean = false,
                    @SerializedName("type")
                    val type: String = "",
                    @SerializedName("url")
                    val url: String = "",
                    @SerializedName("desc")
                    val desc: String = "",
                    @SerializedName("who")
                    val who: String = "")