package com.bw.movie.adapter.cinemaadapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * date:2019.1.27
 * author:赵颖冰(lenovo)
 * function:
 */
public class CinemaDerailPoPuPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] tabNames;
    public CinemaDerailPoPuPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] mTabNames) {
        super(fm);
        this.fragments = fragments;
        this.tabNames=mTabNames;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }
}

