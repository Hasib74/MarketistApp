package com.example.dcl.dailymarketlist.HomePlate.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.HomePlate.Adepter.ConfirmViewPagerAdepter;
import com.example.dcl.dailymarketlist.HomePlate.Adepter.ListViewPagerAdepter;
import com.example.dcl.dailymarketlist.R;

public class Confirm extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    ConfirmViewPagerAdepter confirmViewPagerAdepter;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    int index;
    String data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.confrom_fragment,null);
        tabLayout=v.findViewById(R.id.list_tab_layout);
        viewPager=v.findViewById(R.id.list_view_pager);

        sharedPreferences=getActivity().getSharedPreferences("SP", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        setUpViewPager();

        if (sharedPreferences!=null){
            viewPager.setCurrentItem(sharedPreferences.getInt("position",0));
        }else {
            viewPager.setCurrentItem(0);
        }


        tabLayout.setupWithViewPager(viewPager);
        if (savedInstanceState!=null){
            Toast.makeText(getActivity(), ""+savedInstanceState.getInt("position",0) , Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "Null", Toast.LENGTH_SHORT).show();
        }
        return v;
    }
    private void setUpViewPager() {
        confirmViewPagerAdepter=new ConfirmViewPagerAdepter(getContext(),getFragmentManager());
        viewPager.setAdapter(confirmViewPagerAdepter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onDestroy() {
         super.onDestroy();
          editor.clear();
          editor.putInt("position",viewPager.getCurrentItem());
          editor.commit();
          Toast.makeText(getActivity(), ""+viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
          Toast.makeText(getActivity(), "Destroy", Toast.LENGTH_SHORT).show();
    }
}
