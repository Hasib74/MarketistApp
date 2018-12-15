package com.example.dcl.dailymarketlist.HomePlate.Adepter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dcl.dailymarketlist.HomePlate.ConfirmViewPager.OwnConfrirm;
import com.example.dcl.dailymarketlist.HomePlate.ConfirmViewPager.ReceiveConfirm;
import com.example.dcl.dailymarketlist.HomePlate.ConfirmViewPager.SendConfirm;
import com.example.dcl.dailymarketlist.HomePlate.Fragments.Confirm;
import com.example.dcl.dailymarketlist.HomePlate.ListViewPagerFragments.OwnList;
import com.example.dcl.dailymarketlist.HomePlate.ListViewPagerFragments.ReceiveList;
import com.example.dcl.dailymarketlist.HomePlate.ListViewPagerFragments.SendList;

public class ConfirmViewPagerAdepter extends FragmentStatePagerAdapter {
    private Context context;
    public ConfirmViewPagerAdepter(Context context, FragmentManager fm) {
        super(fm);
        this.context=context;
    }



    @Override
    public Fragment getItem(int i) {
        if (i==0){
            return  new OwnConfrirm();
        }else if (i==1){
            return  new SendConfirm();
        }else {
            return  new ReceiveConfirm();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position ==0){
            return  "Own";
        }else if (position==1){
            return "Send";
        }else {
            return "Receive";
        }
    }
}
