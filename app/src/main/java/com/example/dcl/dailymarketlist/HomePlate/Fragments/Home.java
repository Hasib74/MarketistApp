package com.example.dcl.dailymarketlist.HomePlate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dcl.dailymarketlist.AddMarketList.AddMarketList;
import com.example.dcl.dailymarketlist.R;

public class Home  extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.home_fragment,null);
        CardView add_menu_card=v.findViewById(R.id.add_menu);
        add_menu_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddMarketList.class));
            }
        });
        return v;
    }
}
