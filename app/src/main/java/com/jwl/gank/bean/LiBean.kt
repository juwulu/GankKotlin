package com.jwl.gank.bean

data class LiBean(
    val action_to_last_stick: Int,
    val `data`: List<Data>,
    val feed_flag: Int,
    val has_more: Boolean,
    val has_more_to_refresh: Boolean,
    val hide_topcell_count: Int,
    val login_status: Int,
    val message: String,
    val post_content_hint: String,
    val show_et_status: Int,
    val tips: Tips,
    val total_number: Int
)

data class Data(
    val code: String,
    val content: String
)

data class Tips(
    val app_name: String,
    val display_duration: Int,
    val display_info: String,
    val display_template: String,
    val download_url: String,
    val open_url: String,
    val package_name: String,
    val type: String,
    val web_url: String
)