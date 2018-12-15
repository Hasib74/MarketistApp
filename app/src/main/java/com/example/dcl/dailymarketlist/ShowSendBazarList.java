package com.example.dcl.dailymarketlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dcl.dailymarketlist.Adepter.ShowSendBazarListAdepter;
import com.example.dcl.dailymarketlist.Model.ShowSendBazarListHelper;
import com.example.dcl.dailymarketlist.Retrofit.DailyMarketApi;
import com.example.dcl.dailymarketlist.Utils.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowSendBazarList extends AppCompatActivity {
    String dateAndtime;
    RecyclerView recyclerView;
    DailyMarketApi dailyMarketApi;
    ShowSendBazarListAdepter showSendBazarListAdepter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_send_bazar_list);
        recyclerView=findViewById(R.id.show_send_bazar_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        dailyMarketApi= Common.getApi();

        dateAndtime=getIntent().getStringExtra("dateandtime");

        dailyMarketApi.readShowSendBazarList("01727123374",dateAndtime).enqueue(new Callback<List<ShowSendBazarListHelper>>() {
            @Override
            public void onResponse(Call<List<ShowSendBazarListHelper>> call, Response<List<ShowSendBazarListHelper>> response) {
                showSendBazarListAdepter=new ShowSendBazarListAdepter(getApplicationContext(),response.body());

                 recyclerView.setAdapter(showSendBazarListAdepter);
            }

            @Override
            public void onFailure(Call<List<ShowSendBazarListHelper>> call, Throwable t) {

            }
        });
    }
}
