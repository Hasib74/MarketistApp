package com.example.dcl.dailymarketlist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dcl.dailymarketlist.Adepter.OwnBazarListAdepter;
import com.example.dcl.dailymarketlist.Database.MarketListDB;
import com.example.dcl.dailymarketlist.HomePlate.MainPage;
import com.example.dcl.dailymarketlist.Model.Menu;
import com.example.dcl.dailymarketlist.Utils.Common;

import java.util.ArrayList;
import java.util.List;

public class ShowMenuList extends AppCompatActivity {
    OwnBazarListAdepter ownBazarListAdepter;
    List<Menu> menuList=new ArrayList<>();
    MarketListDB marketListDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu_list);
        marketListDB=new MarketListDB(this);
        RecyclerView recyclerView=findViewById(R.id.show_menu_list);
        recyclerView.setHasFixedSize(true);
        getData(getIntent().getStringExtra("id"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ownBazarListAdepter = new OwnBazarListAdepter(this,menuList);
        recyclerView.setAdapter(ownBazarListAdepter);


    }

    private void getData(String id) {

        menuList.clear();
        Cursor cursor=marketListDB.retriveFullList(id);
        while (cursor.moveToNext()){

            String  info_id=cursor.getString(cursor.getColumnIndex(marketListDB.INFO_ID));
            String  unic_id=cursor.getString(cursor.getColumnIndex(marketListDB.UNIC_ID));
            String menu_name=cursor.getString(cursor.getColumnIndex(marketListDB.MENU_NAME));
            String menuQuantity=cursor.getString(cursor.getColumnIndex(marketListDB.MENU_QUANTITY));
            //Menu menu=new Menu(info_id,unic_id,menu_name,menuQuantity);


            Cursor cursor1=marketListDB.retrivePrice(info_id,unic_id);

            while (cursor1.moveToNext()){
                String price=cursor1.getString(cursor1.getColumnIndex(marketListDB.PRICE));

                if (price != ""){
                    Menu menu=new Menu(info_id,unic_id,menu_name,menuQuantity,price);
                    menuList.add(menu);

                }else {
                    Menu menu=new Menu(info_id,unic_id,menu_name,menuQuantity);
                    menuList.add(menu);
                }

            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Common.load=1;

    }
}
