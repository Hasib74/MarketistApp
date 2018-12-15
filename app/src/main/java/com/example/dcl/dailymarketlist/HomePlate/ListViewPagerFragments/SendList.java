package com.example.dcl.dailymarketlist.HomePlate.ListViewPagerFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dcl.dailymarketlist.Adepter.SenderListAdepter;
import com.example.dcl.dailymarketlist.Model.SendTable;
import com.example.dcl.dailymarketlist.R;
import com.example.dcl.dailymarketlist.Retrofit.DailyMarketApi;
import com.example.dcl.dailymarketlist.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendList extends Fragment {
    SendTable sendTable;
    List<SendTable> sendTableList;
    DailyMarketApi dailyMarketApi;
    SenderListAdepter senderListAdepter;
    RecyclerView recyclerView;

    public SendList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.send,null);
        dailyMarketApi= Common.getApi();
        sendTable=new SendTable();
        sendTableList=new ArrayList<>();
        recyclerView=v.findViewById(R.id.send);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dailyMarketApi.readDataFromSendTable("01727123374").enqueue(new Callback<List<SendTable>>() {
            @Override
            public void onResponse(Call<List<SendTable>> call, Response<List<SendTable>> response) {

                sendTableList=response.body();
                senderListAdepter=new SenderListAdepter(getActivity(),response.body());

                recyclerView.setAdapter(senderListAdepter);

            }

            @Override
            public void onFailure(Call<List<SendTable>> call, Throwable t) {

            }


        });

        return  v;
    }
}
