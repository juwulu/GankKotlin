package com.jwl.gank.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.webkit.WebView

/**
 * author:  lujunwu
 * date:    2018/12/17 16:43
 * desc:    NoDiscription
 */

public class ScrollWebView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : WebView(context, attrs, defStyleAttr) {

    interface OnScollChangeListener{
        fun onTurnDown()
        fun onTurnUp()
    }

    private lateinit var listener:OnScollChangeListener


    fun setOnScrollChangeListener(onScrollChangeListener:OnScollChangeListener){
        listener = onScrollChangeListener
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        Log.d("onScrolllll","l-->$l t-->$t  oldl-->$oldl oldt-->$oldt")
        if ((t-oldt)>0){
            listener.onTurnUp()
        }else if ((t-oldt)<0){
            listener.onTurnDown()
        }
    }



}