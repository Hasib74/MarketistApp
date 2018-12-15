package com.example.dcl.dailymarketlist;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dcl.dailymarketlist.Fragment.OwnMarket;
import com.example.dcl.dailymarketlist.Fragment.Send;
import com.example.dcl.dailymarketlist.Fragment.Fragment_C;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new OwnMarket();
        } else if (position == 1){
            return new Send();
        } else {
            return new Fragment_C();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3; // Generate title based on item position
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "OwnConfrirm";
            case 1:
                return "SendConfirm";
            case 2:
                return "ReceiveConfirm";
            default:
                return null;
        }
    }

}