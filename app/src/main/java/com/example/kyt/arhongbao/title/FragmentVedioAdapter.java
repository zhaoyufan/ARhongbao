package com.example.kyt.arhongbao.title;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by kyt on 2017/2/21.
 */
public class FragmentVedioAdapter extends FragmentStatePagerAdapter {
    private Context mContext;

    private Fragment[] mFragments = null;
    private String[] mFragmentTitles = null;

    public FragmentVedioAdapter(Context mContext, FragmentManager fm, Fragment[] mFragments, String[] mFragmentTitles) {
        super(fm);
        this.mContext = mContext;
        this.mFragments = mFragments;
        this.mFragmentTitles = mFragmentTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles[position];
    }
}
