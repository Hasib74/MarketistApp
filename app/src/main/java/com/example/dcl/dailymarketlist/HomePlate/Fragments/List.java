package com.example.dcl.dailymarketlist.HomePlate.Fragments;

import android.annotation.SuppressLint;
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
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.HomePlate.Adepter.ListViewPagerAdepter;
import com.example.dcl.dailymarketlist.R;

public class List  extends Fragment{
    TabLayout tabLayout;
    ViewPager  viewPager;
    ListViewPagerAdepter listViewPagerAdepter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    int index;

    public List() {

    }

    public static List newInstance(int index) {
        List f = new List();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }



    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            index = bundle.getInt("index");

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.list_fragment,null);

         sharedPreferences=getActivity().getSharedPreferences("SP1", Context.MODE_PRIVATE);
         editor=sharedPreferences.edit();


         readBundle(getArguments());


         tabLayout=v.findViewById(R.id.list_tab_layout);
         viewPager=v.findViewById(R.id.list_view_pager);
         Toast.makeText(getActivity(), "Index :"+index, Toast.LENGTH_SHORT).show();

         setUpViewPager();

        if (sharedPreferences!=null){
            viewPager.setCurrentItem(sharedPreferences.getInt("position",0));
        }else {
            viewPager.setCurrentItem(0);
        }


        tabLayout.setupWithViewPager(viewPager);
         return v;
    }

    private void setUpViewPager() {


            Toast.makeText(getActivity(), "I am alive", Toast.LENGTH_SHORT).show();
            listViewPagerAdepter=new ListViewPagerAdepter(getContext(),getFragmentManager());
            viewPager.setAdapter(listViewPagerAdepter);
            viewPager.setCurrentItem(0);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        editor.clear();
        editor.putInt("position",viewPager.getCurrentItem());
        editor.commit();

    }
}
