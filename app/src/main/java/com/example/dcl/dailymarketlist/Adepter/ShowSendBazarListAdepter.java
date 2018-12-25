package com.example.dcl.dailymarketlist.Adepter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dcl.dailymarketlist.Model.ReceivedData;
import com.example.dcl.dailymarketlist.Model.ShowSendBazarListHelper;
import com.example.dcl.dailymarketlist.R;
import com.example.dcl.dailymarketlist.ShowSendBazarList;

import java.util.List;

public class ShowSendBazarListAdepter extends RecyclerView.Adapter<ShowSendBazarListAdepter.ShowSendBazarListViewHolder> {
    Context context;
    List<ShowSendBazarListHelper> showSendBazarListList;

    public ShowSendBazarListAdepter(Context context, List<ShowSendBazarListHelper> showSendBazarListList) {
        this.context = context;
        this.showSendBazarListList = showSendBazarListList;
    }



    @NonNull
    @Override
    public ShowSendBazarListAdepter.ShowSendBazarListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_send_bazar_list_design,null);
        return new ShowSendBazarListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowSendBazarListAdepter.ShowSendBazarListViewHolder showSendBazarListViewHolder, int i) {
        showSendBazarListViewHolder.item_name.setText(showSendBazarListList.get(i).getItemname());
        showSendBazarListViewHolder.quantity.setText(showSendBazarListList.get(i).getQuantity());
    }

    @Override
    public int getItemCount() {
        return showSendBazarListList.size();
    }

    public class ShowSendBazarListViewHolder extends RecyclerView.ViewHolder {
        TextView item_name,quantity;
        public ShowSendBazarListViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name=itemView.findViewById(R.id.item_name);
            quantity=itemView.findViewById(R.id.quantity);
        }
    }
}
