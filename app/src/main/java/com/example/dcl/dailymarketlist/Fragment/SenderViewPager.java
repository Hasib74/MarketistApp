package com.example.dcl.dailymarketlist.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dcl.dailymarketlist.Fragment.SendMarketFragment.ConfromList;
import com.example.dcl.dailymarketlist.Fragment.SendMarketFragment.SendList;

public class SenderViewPager extends FragmentPagerAdapter {
    public SenderViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i==0){
           return  new SendList();
        }else {
            return  new ConfromList();
        }


    }

    @Override
    public int getCount() {
        return 2;
    }
}
