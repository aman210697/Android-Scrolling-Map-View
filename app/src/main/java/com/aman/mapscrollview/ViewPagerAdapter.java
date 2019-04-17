package com.aman.mapscrollview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Location> list ;
    List<LocationFragment> fragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, List<Location> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        LocationFragment fragment = LocationFragment.newInstance(list.get(i));
        fragmentList.add(fragment);
        return  fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
