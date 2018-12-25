package com.example.dcl.dailymarketlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dcl.dailymarketlist.Adepter.ShowSendBazarListAdepter;
import com.example.dcl.dailymarketlist.Model.ReceivedData;
import com.example.dcl.dailymarketlist.Model.ShowSendBazarListHelper;
import com.example.dcl.dailymarketlist.Retrofit.DailyMarketApi;
import com.example.dcl.dailymarketlist.Utils.Common;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowSendBazarList extends AppCompatActivity {
    String dateAndtime;
    RecyclerView recyclerView;
    DailyMarketApi dailyMarketApi;
    ShowSendBazarListAdepter showSendBazarListAdepter;
    String class_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_send_bazar_list);
        recyclerView=findViewById(R.id.show_send_bazar_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        dailyMarketApi= Common.getApi();

        dateAndtime=getIntent().getStringExtra("dateandtime");
        class_path=getIntent().getStringExtra("class_path");

        if (class_path.equals("received")){

            dailyMarketApi.readShowReceivedBazarList(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(1),dateAndtime).enqueue(new Callback<List<ShowSendBazarListHelper>>() {
                @Override
                public void onResponse(Call<List<ShowSendBazarListHelper>> call, Response<List<ShowSendBazarListHelper>> response) {
                    showSendBazarListAdepter=new ShowSendBazarListAdepter(getApplicationContext(),response.body());

                    recyclerView.setAdapter(showSendBazarListAdepter);
                }

                @Override
                public void onFailure(Call<List<ShowSendBazarListHelper>> call, Throwable t) {

                }
            });

        }else if (class_path.equals("send")){
            dailyMarketApi.readShowSendBazarList(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(1),dateAndtime).enqueue(new Callback<List<ShowSendBazarListHelper>>() {
                @Override
                public void onResponse(Call<List<ShowSendBazarListHelper>> call, Response<List<ShowSendBazarListHelper>> response) {
                    showSendBazarListAdepter=new ShowSendBazarListAdepter(getApplicationContext(),response.body());
                    recyclerView.setAdapter(showSendBazarListAdepter);
                }

                @Override
                public void onFailure(Call<List<ShowSendBazarListHelper>> call, Throwable t) {

                }
            });
        }else {
            return;
        }


    }
}
