package com.example.dcl.dailymarketlist.Adepter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dcl.dailymarketlist.Model.ReceivedData;
import com.example.dcl.dailymarketlist.Model.SendTable;
import com.example.dcl.dailymarketlist.R;
import com.example.dcl.dailymarketlist.Retrofit.DailyMarketApi;
import com.example.dcl.dailymarketlist.Utils.Common;

import java.util.List;

public class ReadAllDataFromReceivedTableAdepter extends RecyclerView.Adapter<ReadAllDataFromReceivedTableAdepter.ViewHolderClass> {
    Context context;
    List<ReceivedData> received_data_list;
    DailyMarketApi dailyMarketApi;

    public ReadAllDataFromReceivedTableAdepter(Context context, List<ReceivedData> received_data_list1) {
        this.context = context;

        for (int i=0;i<received_data_list1.size()-1;i++){
            for (int j=1;j<received_data_list1.size();j++){
                if (received_data_list1.get(i).getDateandtime().equals(received_data_list1.get(j).getDateandtime())){
                    received_data_list1.remove(j);
                }
            }
        }
        received_data_list = received_data_list1;
       // this.received_data_list = received_data_list;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sender_list_design,null);

        dailyMarketApi= Common.getApi();
        return new ViewHolderClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass viewHolderClass, final int i) {
        String[] dateandtimeArrqay=received_data_list.get(i).getDateandtime().split(",");
        String date=dateandtimeArrqay[0];
        String time=dateandtimeArrqay[1];


        viewHolderClass.date.setText(date);
        viewHolderClass.time.setText(time);

        viewHolderClass.receiver_number.setText(received_data_list.get(i).getSenderid());

        viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(context, com.example.dcl.dailymarketlist.ShowSendBazarList.class);
                in.putExtra("class_path","received");
                in.putExtra("dateandtime",received_data_list.get(i).getDateandtime());
                context.startActivity(in);
            }
        });


    }

    @Override
    public int getItemCount() {
        return received_data_list.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView date;
        TextView time;
        TextView  receiver_number;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            receiver_number=itemView.findViewById(R.id.receiver_number);
        }
    }
}
