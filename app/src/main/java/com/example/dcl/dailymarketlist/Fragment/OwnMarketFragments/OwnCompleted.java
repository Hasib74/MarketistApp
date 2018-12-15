package com.example.dcl.dailymarketlist.Fragment.OwnMarketFragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dcl.dailymarketlist.Adepter.CompletedBazarListAdepter;
import com.example.dcl.dailymarketlist.Database.MarketListDB;
import com.example.dcl.dailymarketlist.Interface.OwnBazarClick;
import com.example.dcl.dailymarketlist.Model.CompleteMarket;
import com.example.dcl.dailymarketlist.R;
import com.example.dcl.dailymarketlist.ShowCompletedListMenus;
import com.example.dcl.dailymarketlist.Utils.Common;

import java.util.ArrayList;
import java.util.List;

public class OwnCompleted extends Fragment {
    RecyclerView recyclerView;
    MarketListDB marketListDB;
    List<CompleteMarket> completeMarketList=new ArrayList<>();
    CompletedBazarListAdepter completedBazarListAdepter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.own_complete_list,null);
        recyclerView=v.findViewById(R.id.compleded_market_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        marketListDB=new MarketListDB(getContext());
        setUp_completed_market_list();
        load();

        completedBazarListAdepter.setOwnBazarClick(new OwnBazarClick() {
            @Override
            public void onOwnBazarItemClick(View v, int possition) {
                Intent in=new Intent(getActivity(), ShowCompletedListMenus.class);
                in.putExtra("key_id",completeMarketList.get(possition).getId());
                startActivity(in);
            }
        });

        return v;

    }

    private void load() {
        completedBazarListAdepter=new CompletedBazarListAdepter(getContext(),completeMarketList);
        recyclerView.setAdapter(completedBazarListAdepter);
    }

    private void setUp_completed_market_list() {
        Cursor cursor=marketListDB.CompletedBazarList();
        while (cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndex(marketListDB.KEY_ID));
            String date=cursor.getString(cursor.getColumnIndex(marketListDB.DATE));
            String time=cursor.getString(cursor.getColumnIndex(marketListDB.TIME));
            completeMarketList.add(new CompleteMarket(id,date,time));

        }

     //   Common.completeMarketList=completeMarketList;
    }
}
