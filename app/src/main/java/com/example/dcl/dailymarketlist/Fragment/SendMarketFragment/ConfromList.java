package com.example.dcl.dailymarketlist.Fragment.SendMarketFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dcl.dailymarketlist.R;

public class ConfromList extends Fragment {
    public ConfromList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=LayoutInflater.from(container.getContext()).inflate(R.layout.confrom_list,null);
        return v;
    }
}
