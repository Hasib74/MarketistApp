package com.example.dcl.dailymarketlist;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.Adepter.CompletedBazarListAdepter;
import com.example.dcl.dailymarketlist.Adepter.CompletedBazarListMenuAdepter;
import com.example.dcl.dailymarketlist.Database.MarketListDB;
import com.example.dcl.dailymarketlist.Model.CompletedBaxarListMenuModel;

import java.util.ArrayList;
import java.util.List;

public class ShowCompletedListMenus extends AppCompatActivity {
    RecyclerView recyclerView;
    List<CompletedBaxarListMenuModel> completedBaxarListMenuModelList=new ArrayList<>();
    MarketListDB marketListDB;
    String id;
    CompletedBazarListMenuAdepter completedBazarListMenuAdepter;
    public  int b=0;
    TextView total_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_completed_list);
        recyclerView=findViewById(R.id.completedListMenus);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        marketListDB=new MarketListDB(this);
        total_price=findViewById(R.id.total_price);

        id=getIntent().getStringExtra("key_id");


        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();

        loadOperation(id);
        load();

        for (int i=0;i<completedBaxarListMenuModelList.size();i++){
            int a=Integer.parseInt(completedBaxarListMenuModelList.get(i).getPrice());
            b+=a;
        }
        total_price.setText(new StringBuilder().append("Total Amount : ").append(String.valueOf(b)));



    }

    private void load() {
        completedBazarListMenuAdepter=new CompletedBazarListMenuAdepter(this,completedBaxarListMenuModelList);
        recyclerView.setAdapter(completedBazarListMenuAdepter);
    }

    private void loadOperation(String id) {
        Cursor cursor1=marketListDB.getAllCompletedMenus(id);
        while (cursor1.moveToNext()){
            String menu_name=cursor1.getString(cursor1.getColumnIndex(marketListDB.MENU_NAME));
            String menu_quantity=cursor1.getString(cursor1.getColumnIndex(marketListDB.MENU_QUANTITY));
            String menu_price=cursor1.getString(cursor1.getColumnIndex(marketListDB.PRICE));
            CompletedBaxarListMenuModel completedBaxarListMenuModel=new CompletedBaxarListMenuModel(menu_name,menu_quantity,menu_price);
            completedBaxarListMenuModelList.add(completedBaxarListMenuModel);
        }
    }

  /*  private void operation() {

        Cursor cursor=marketListDB.getAllCompletedStatusKeyId();
        while (cursor!=null){
            String key_id=cursor.getString(cursor.getColumnIndex(marketListDB.KEY_ID));
            Cursor cursor1=marketListDB.getAllCompletedMenus(key_id);
            while (cursor1!=null){
                String menu_name=cursor1.getString(cursor1.getColumnIndex(marketListDB.MENU_NAME));
                String menu_quantity=cursor1.getString(cursor1.getColumnIndex(marketListDB.MENU_QUANTITY));
                String menu_price=cursor1.getString(cursor1.getColumnIndex(marketListDB.PRICE));

                CompletedBaxarListMenuModel completedBaxarListMenuModel=new CompletedBaxarListMenuModel()
            }
        }
    }*/
}
