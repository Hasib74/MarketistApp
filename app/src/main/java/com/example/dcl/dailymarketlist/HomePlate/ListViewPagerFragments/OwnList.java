package com.example.dcl.dailymarketlist.HomePlate.ListViewPagerFragments;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.Adepter.MinimizeAdepter;
import com.example.dcl.dailymarketlist.Adepter.OwnBazarAdepter;
import com.example.dcl.dailymarketlist.Adepter.OwnBazarListAdepter;
import com.example.dcl.dailymarketlist.Database.MarketListDB;
import com.example.dcl.dailymarketlist.Interface.OwnBazarClick;
import com.example.dcl.dailymarketlist.Model.Menu;
import com.example.dcl.dailymarketlist.Model.Minimize;
import com.example.dcl.dailymarketlist.Model.OwnBazar;
import com.example.dcl.dailymarketlist.R;
import com.example.dcl.dailymarketlist.ShowMenuList;
import com.example.dcl.dailymarketlist.Utils.Common;

import java.util.ArrayList;
import java.util.List;

public class OwnList extends Fragment {
    RecyclerView recyclerView;
    RecyclerView minimize_recycularView;
    List<OwnBazar> own_bazar_list=new ArrayList<>();
    List<Menu> menuList=new ArrayList<>();
    List<Minimize> minimizeList=new ArrayList<>();

    MarketListDB marketListDB;
    OwnBazarAdepter ownBazarAdepter;
    OwnBazarListAdepter ownBazarListAdepter;
    MinimizeAdepter minimizeAdepter;
    String  total_item,confrom_item ;
    ImageView btn;
    LinearLayout minimization_layout;

    public  int index;
    public OwnList() {
    }

    @Override
    public void onStart() {
        super.onStart();

        load();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "Reload", Toast.LENGTH_SHORT).show();
        if (Common.load==1){
            retrive_data_from_database();
            load();
            Common.load=0;
        }

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View v=inflater.inflate(R.layout.own,null);
        Toast.makeText(getContext(), "Fuck "+index, Toast.LENGTH_SHORT).show();

            recyclerView=v.findViewById(R.id.own_market_list);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            marketListDB=new MarketListDB(getContext());
            retrive_data_from_database();

        Toast.makeText(getContext(), "Load "+Common.load, Toast.LENGTH_SHORT).show();



        return  v;
    }

    private void load() {
        ownBazarAdepter=new OwnBazarAdepter(getContext(),own_bazar_list);
        recyclerView.setAdapter(ownBazarAdepter);
    }

    private void retrive_data_from_database() {

        Cursor cursor=marketListDB.retriveOwnBazar();

        own_bazar_list.clear();
        while (cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndex(marketListDB.KEY_ID));
            String date= cursor.getString(cursor.getColumnIndex(marketListDB.DATE));
            String time=cursor.getString(cursor.getColumnIndex(marketListDB.TIME));
            List<String> count=marketListDB.count(id);

            own_bazar_list.add(new OwnBazar(id,date,time,new StringBuilder().append(count.get(1)).append("/").append(count.get(0)).toString()));
        }
        load();
    }

}
