package com.jwl.gank.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class HomeAdapter: FragmentPagerAdapter {

    var mFragments: List<Fragment> = emptyList<Fragment>()
    constructor(fm:FragmentManager,fragments:List<Fragment>):super(fm){
        mFragments = fragments;
    }
    override fun getItem(p0: Int): Fragment {
        return mFragments.get(p0)
    }

    override fun getCount(): Int {
        return mFragments.size
    }

}