package com.example.titaniwmbluetoothcontrol.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GerarSckecthAdapter extends FragmentPagerAdapter {
    private final List<String> mTabTitles = new ArrayList<>();
    private final List<Fragment> mfragmentList = new ArrayList<>();


    public GerarSckecthAdapter(FragmentManager fm) {
        super(fm);


    }

    @Override
    public Fragment getItem(int i) {
        /*switch(i)
        {
            case 0:
                return new FragmentPanelSkecth();
            case 1:
                return new FragmentGerarSkecth();
            default:
                return null;
        }

         */
        return mfragmentList.get(i);


    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int i)
    {
        return mTabTitles.get(i);
    }


    public void addFrag(Fragment fragment, String title)
    {
        mfragmentList.add(fragment);
        mTabTitles.add(title);


    }



}


