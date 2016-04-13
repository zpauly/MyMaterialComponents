package com.zpauly.sample.floatingactionbutton;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-4-10.
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mFragmentTitles;

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<Fragment>();
        mFragmentTitles = new ArrayList<String>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }
}
