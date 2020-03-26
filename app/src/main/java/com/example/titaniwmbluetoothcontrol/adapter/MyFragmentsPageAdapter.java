package com.example.titaniwmbluetoothcontrol.adapter;




import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.titaniwmbluetoothcontrol.BuscaFragment;
import com.example.titaniwmbluetoothcontrol.PareadosActivity;


public class MyFragmentsPageAdapter extends FragmentPagerAdapter {

    private String[] mTabTitles;

    public MyFragmentsPageAdapter(FragmentManager fm, String[]  mbTabTitles) {
        super(fm);


        this.mTabTitles = mbTabTitles;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i)
        {
            case 0:
                return new BuscaFragment();
            case 1:
                return new PareadosActivity();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return this.mTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int i)
    {
        return this.mTabTitles[i];
    }





}


