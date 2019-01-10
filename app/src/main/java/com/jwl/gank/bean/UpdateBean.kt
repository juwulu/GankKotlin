package com.jwl.gank.bean


import com.google.gson.annotations.SerializedName

data class Update(@SerializedName("downloadUrl")
                val downloadUrl: String = "",
                @SerializedName("packageName")
                val packageName: String = "",
                @SerializedName("versionName")
                val versionName: String = "",
                @SerializedName("versionCode")
                val versionCode: Int = 0,
                @SerializedName("desc")
                val desc: String = "")


data class UpdateBean(@SerializedName("data")
                      val data: Update,
                      @SerializedName("error")
                      val error: Boolean = false)


