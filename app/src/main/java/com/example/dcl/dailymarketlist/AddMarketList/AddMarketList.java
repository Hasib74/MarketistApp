package com.example.dcl.dailymarketlist.AddMarketList;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.Adepter.AddManuAdepter;
import com.example.dcl.dailymarketlist.Database.MarketListDB;
import com.example.dcl.dailymarketlist.HomePlate.MainPage;
import com.example.dcl.dailymarketlist.Market;
import com.example.dcl.dailymarketlist.Model.AddMenu;
import com.example.dcl.dailymarketlist.R;
import com.example.dcl.dailymarketlist.Utils.Common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.dcl.dailymarketlist.Adepter.AddManuAdepter.quntity_markes_2;

public class AddMarketList extends AppCompatActivity {

    public  RecyclerView recyclerView;
    List<AddMenu> add_menu_list;
    AddManuAdepter addManuAdepter;
    Button cancel_btn,save_btn;
    List<AddMenu> final_list=new ArrayList<>();
    MarketListDB marketListDB;
    public static String change_item="",change_quantity="",change_quantity_types = "kg";
    private   String time;
    String current_date;
    private static final int FOR_HOURS = 3600000;
    private static final int FOR_MIN = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_market_list);
        add_menu_list=new ArrayList<>();
        recyclerView=findViewById(R.id.recycular_view);
        cancel_btn=findViewById(R.id.cancel);
        save_btn=findViewById(R.id.save);

        current_date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        addManuAdepter=new AddManuAdepter(add_menu_list,this);
        loadData();

        marketListDB=new MarketListDB(this);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int position = add_menu_list.size()-1;
                Toast.makeText(AddMarketList.this, change_quantity_types, Toast.LENGTH_SHORT).show();




                if( !change_item.equals("") || !change_quantity.equals(""))
                {
                    add_menu_list.remove(position);
                    add_menu_list.add(new AddMenu(change_item,change_quantity, quntity_markes_2));
                }

                    for (int i=0;i<add_menu_list.size();i++){

                        String menu_name=add_menu_list.get(i).getMenu_name();
                        String menu_quantoty=add_menu_list.get(i).getQuantity();

                        if (menu_name.equals("") || menu_quantoty.equals("")){
                            final_list.add(new AddMenu(menu_name,menu_quantoty));
                        }else {

                            for(int j =0 ; j < add_menu_list.size();j++ )
                            {
                                System.out.println( add_menu_list.size() +" "+ add_menu_list.get(j).getMenu_name()+" " + add_menu_list.get(j).getQuantity());
                            }
                            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();

                        }
                    }

                    String saved =marketListDB.InsertIntoCreateTable(Common.phone_number,current_date,Common.getTime());

                  Toast.makeText(AddMarketList.this, ""+saved, Toast.LENGTH_SHORT).show();

                  String last_key=marketListDB.selectLastIdOfCreateTable();
                  for (int i=0;i<add_menu_list.size();i++){
                      marketListDB.addListIntoTheListTable(last_key,add_menu_list.get(i).getMenu_name(), add_menu_list.get(i).getQuantity()+" "+add_menu_list.get(i).getQuntity_marks()/*,Common.getTime()*/);
                  }

                /*  for (int i=0;i<add_menu_list.size();i++){
                      String list=add_menu_list.get(i).getQuntity_marks();
                   //   Log.d("MAR",list);
                      System.out.println("MAR"+list);
                  }
*/


       Intent in =new Intent(AddMarketList.this, MainPage.class);
       in.putExtra("complete","add");
       startActivity(in);


            }
        });
    }

    public void loadData()
    {
        recyclerView.setAdapter(addManuAdepter);
    }




}
