package com.example.dcl.dailymarketlist.HomePlate.ConfirmViewPager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dcl.dailymarketlist.R;

public class SendConfirm extends Fragment {
    public SendConfirm() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.send,null);

        return  v;
    }
}
