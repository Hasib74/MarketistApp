package com.example.dcl.dailymarketlist.HomePlate.Defult;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.R;

public class DefultFram extends Fragment {

    String key;
    String date;
    String time;

    public static DefultFram newInstance(String key,String date,String time) {
        Bundle args = new Bundle();
        args.putString("key",key);
        args.putString("date",date);
        args.putString("time",time);
        DefultFram fragment = new DefultFram();
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
        getData(getArguments());
        View v=inflater.inflate(R.layout.defult_fram,null);
        v.requestFocus();
        v.setFocusableInTouchMode(true);
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode==KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP){
                    Toast.makeText(getContext(), "Back press", Toast.LENGTH_SHORT).show();
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return  true;
                }
                return false;
            }
        });
        Toast.makeText(getContext(), ""+date, Toast.LENGTH_SHORT).show();
       return v;
    }

}
