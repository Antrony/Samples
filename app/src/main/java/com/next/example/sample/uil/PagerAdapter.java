package com.next.example.sample.uil;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by next on 28/9/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragments tab1 = new TabFragments();
                return tab1;
            case 1:
                TabFragments1 tab2 = new TabFragments1();
                return tab2;
            case 2:
                TabFragments2 tab3 = new TabFragments2();
                return tab3;
            case 3:
                TabFragments3 tab4 = new TabFragments3();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
