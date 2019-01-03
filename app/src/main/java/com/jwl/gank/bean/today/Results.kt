package com.jwl.gank.bean.today

import com.google.gson.annotations.SerializedName

data class Results(@SerializedName("App")
                   val app: List<AppItem>?,
                   @SerializedName("休息视频")
                   val 休息视频: List<休息视频Item>?,
                   @SerializedName("福利")
                   val 福利: List<福利Item>?,
                   @SerializedName("拓展资源")
                   val 拓展资源: List<拓展资源Item>?,
                   @SerializedName("前端")
                   val 前端: List<前端Item>?,
                   @SerializedName("瞎推荐")
                   val 瞎推荐: List<瞎推荐Item>?,
                   @SerializedName("iOS")
                   val iOS: List<IOSItem>?,
                   @SerializedName("Android")
                   val android: List<AndroidItem>?)