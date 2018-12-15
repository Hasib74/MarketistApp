package com.example.dcl.dailymarketlist.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dcl.dailymarketlist.Fragment.OwnMarketFragments.OwnMarketList;
import com.example.dcl.dailymarketlist.Fragment.OwnMarketFragments.Fragment_three;
import com.example.dcl.dailymarketlist.Fragment.OwnMarketFragments.OwnCompleted;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

       if (i==0){
           return  new OwnMarketList();
       }else if (i==1){
           return  new OwnCompleted();
       }else {
           return new Fragment_three();
       }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
