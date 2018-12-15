package com.example.dcl.dailymarketlist.Adepter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dcl.dailymarketlist.Interface.OwnBazarClick;
import com.example.dcl.dailymarketlist.Model.CompleteMarket;
import com.example.dcl.dailymarketlist.R;

import java.util.ArrayList;
import java.util.List;

public class CompletedBazarListAdepter extends RecyclerView.Adapter<CompletedBazarListAdepter.CompletedBazarListViewHolder> {
    public Context context;
    public List<CompleteMarket> completeMarketList=new ArrayList<>();

    private OwnBazarClick ownBazarClick;

    public void setOwnBazarClick(OwnBazarClick ownBazarClick) {
        this.ownBazarClick = ownBazarClick;
    }

    public CompletedBazarListAdepter(Context context, List<CompleteMarket> completeMarketList) {
        this.context = context;
        this.completeMarketList = completeMarketList;
    }

    @NonNull
    @Override
    public CompletedBazarListAdepter.CompletedBazarListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.own_market_completed_list_design,null);
        return new CompletedBazarListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedBazarListAdepter.CompletedBazarListViewHolder completedBazarListViewHolder, int i) {
      completedBazarListViewHolder.date.setText(completeMarketList.get(i).getDate());
      completedBazarListViewHolder.time.setText(completeMarketList.get(i).getTime());
    }

    @Override
    public int getItemCount() {
        return completeMarketList.size();
    }

    public class CompletedBazarListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView date,time;
        public CompletedBazarListViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ownBazarClick.onOwnBazarItemClick(v,getAdapterPosition());
        }
    }
}
