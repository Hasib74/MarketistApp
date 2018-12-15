package com.example.dcl.dailymarketlist.HomePlate.Defult;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dcl.dailymarketlist.R;

public class DefultListShowFragment extends Fragment {
    String key;
    String date;
    String time;

    public static DefultListShowFragment newInstance(String key,String date,String time) {
        Bundle args = new Bundle();
        args.putString("key",key);
        args.putString("date",date);
        args.putString("time",time);
        DefultListShowFragment fragment = new DefultListShowFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public  void getData(Bundle bundle){
        key=bundle.getString("key");
        date=bundle.getString("date");
        time=bundle.getString("time");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View v=inflater.inflate(R.layout.defult_show_list_fragment,null);
     getData(getArguments());
     return  v;
    }
}
