package com.example.dcl.dailymarketlist.Adepter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dcl.dailymarketlist.Model.Minimize;
import com.example.dcl.dailymarketlist.R;

import java.util.ArrayList;
import java.util.List;

public class MinimizeAdepter extends RecyclerView.Adapter<MinimizeAdepter.MinimizeViewHolde> {
    public Context context;
    public  List<Minimize> minimizeList=new ArrayList<>();


    public MinimizeAdepter(Context context, List<Minimize> minimizeList) {
        this.context = context;
        this.minimizeList = minimizeList;
    }

    @NonNull
    @Override
    public MinimizeViewHolde onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.minimize_design,null);
        return new MinimizeViewHolde(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MinimizeViewHolde minimizeViewHolde, int i) {
        minimizeViewHolde.date.setText(minimizeList.get(i).getDate());
        minimizeViewHolde.time.setText(minimizeList.get(i).getTime());

    }

    @Override
    public int getItemCount() {
        return minimizeList.size();
    }

    public class MinimizeViewHolde extends RecyclerView.ViewHolder {
        TextView date,time;

        public MinimizeViewHolde(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
        }
    }
}
