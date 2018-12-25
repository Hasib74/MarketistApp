package com.example.dcl.dailymarketlist.Adepter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dcl.dailymarketlist.Model.SendTable;
import com.example.dcl.dailymarketlist.R;
import com.example.dcl.dailymarketlist.Retrofit.DailyMarketApi;
import com.example.dcl.dailymarketlist.Utils.Common;

import java.util.ArrayList;
import java.util.List;

public class SenderListAdepter  extends RecyclerView.Adapter<SenderListAdepter.SenderListViewHlder> {
    Context context;
    List<SendTable> sendTableList;
    DailyMarketApi dailyMarketApi;


    public SenderListAdepter(Context context, List<SendTable> SendTableList1) {
        this.context = context;

        List<SendTable> SendTableList=SendTableList1;
        List<SendTable> tempList=new ArrayList<>();

        for (int i=0;i<SendTableList.size()-1;i++){
            for (int j=1;j<SendTableList.size();j++){
                if (SendTableList.get(i).getDateandtime().equals(SendTableList.get(j).getDateandtime())){
                    SendTableList.remove(j);
                }
            }
        }
       sendTableList = SendTableList;

    }

    @NonNull
    @Override
    public SenderListViewHlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sender_list_design,null);

        dailyMarketApi= Common.getApi();
        return new SenderListViewHlder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SenderListViewHlder senderListViewHlder, final int i) {





        String dateandtime=sendTableList.get(i).getDateandtime();
        String[] dateandtimeArrqay=dateandtime.split(",");
        String date=dateandtimeArrqay[0];
        String time=dateandtimeArrqay[1];


        senderListViewHlder.date.setText(date);
        senderListViewHlder.time.setText(time);

        senderListViewHlder.receiver_number.setText(sendTableList.get(i).getReceiveid());

       senderListViewHlder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in=new Intent(context, com.example.dcl.dailymarketlist.ShowSendBazarList.class);
               in.putExtra("class_path","send");
               in.putExtra("dateandtime",sendTableList.get(i).getDateandtime());
               context.startActivity(in);
           }
       });




    }

    @Override
    public int getItemCount() {
        return sendTableList.size();
    }

    public class SenderListViewHlder extends RecyclerView.ViewHolder {
        TextView date;
        TextView time;
        TextView  receiver_number;
        public SenderListViewHlder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            receiver_number=itemView.findViewById(R.id.receiver_number);


        }


    }
}
