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
import android.widget.Toast;

import com.example.dcl.dailymarketlist.Adepter.ReadAllDataFromReceivedTableAdepter;
import com.example.dcl.dailymarketlist.Model.ReceivedData;
import com.example.dcl.dailymarketlist.R;
import com.example.dcl.dailymarketlist.Retrofit.DailyMarketApi;
import com.example.dcl.dailymarketlist.Utils.Common;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiveList extends Fragment {
    ReadAllDataFromReceivedTableAdepter readAllDataFromReceivedTableAdepter;
    RecyclerView recyclerView;
    DailyMarketApi myApi;

    public ReceiveList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.receive,null);
        recyclerView =v.findViewById(R.id.receive);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
       // readAllDataFromReceivedTableAdepter=new ReadAllDataFromReceivedTableAdepter(th)
        myApi= Common.getApi();

        loadData();
        return  v;
    }

    private void loadData() {

        myApi.readDataFromReceivedTable(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(1)).enqueue(new Callback<List<ReceivedData>>() {
            @Override
            public void onResponse(Call<List<ReceivedData>> call, Response<List<ReceivedData>> response) {
                readAllDataFromReceivedTableAdepter=new ReadAllDataFromReceivedTableAdepter(getActivity(),response.body());
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(readAllDataFromReceivedTableAdepter);
            }

            @Override
            public void onFailure(Call<List<ReceivedData>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
