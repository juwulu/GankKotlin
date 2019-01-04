package com.jwl.gank.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * author:  lujunwu
 * date:    2019/1/4 11:22
 * desc:    NoDiscription
 */
public class ReadPagerAdapter(var fragments:MutableList<Fragment>,var titles:MutableList<String>, fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return fragments[p0]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}