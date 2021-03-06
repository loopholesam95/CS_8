package com.example.subhadeep95.cs_8;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPageAdapter extends FragmentPagerAdapter {

    CharSequence Titles[];
    int NoOfPages;

    public ViewPageAdapter(FragmentManager manager,CharSequence Titles[],int NoofPages) {
        super(manager);
        this.Titles=Titles;
        this.NoOfPages=NoofPages;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new slide1();
        else
            return new slide2();
    }

    @Override
    public int getCount() {
        return NoOfPages;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }
}