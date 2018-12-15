package com.example.dcl.dailymarketlist.Adepter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.Database.MarketListDB;
import com.example.dcl.dailymarketlist.Interface.FCMservice;
import com.example.dcl.dailymarketlist.MainActivity;
import com.example.dcl.dailymarketlist.Market;
import com.example.dcl.dailymarketlist.Model.FCMResponse;
import com.example.dcl.dailymarketlist.Model.MemberNumber;
import com.example.dcl.dailymarketlist.Model.Notification;
import com.example.dcl.dailymarketlist.Model.Sender;
import com.example.dcl.dailymarketlist.Model.Token;
import com.example.dcl.dailymarketlist.Model.check1;
import com.example.dcl.dailymarketlist.R;
import com.example.dcl.dailymarketlist.Retrofit.DailyMarketApi;
import com.example.dcl.dailymarketlist.Utils.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogMembersAdepter extends RecyclerView.Adapter<DialogMembersAdepter.DilogMembersViewHolder> {
    public Context context;
    public List<MemberNumber> memberNumberList;
    public String date;
    public String time;
    public String id;
    String token_key;
    FCMservice fcMservice;

    public  MarketListDB marketListDB;

    //public String item_name;
  //  public  String quantity;



    DailyMarketApi dailyMarketApi;

    public DialogMembersAdepter(Context context, List<MemberNumber> memberNumberList, String date, String time, String id) {
        this.context = context;
        this.memberNumberList = memberNumberList;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    @NonNull
    @Override
    public DialogMembersAdepter.DilogMembersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        marketListDB=new MarketListDB(context);
        dailyMarketApi= Common.getApi();
        fcMservice=Common.getFCMService();
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_send_to_members_design,null);
        return new DilogMembersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DialogMembersAdepter.DilogMembersViewHolder dilogMembersViewHolder, int i) {
          dilogMembersViewHolder.member_number.setText(memberNumberList.get(i).getNumber());

          dilogMembersViewHolder.send.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Cursor cursor=marketListDB.retriveFullList(id);


                  while (cursor.moveToNext()){

                      String  item_name=cursor.getString(cursor.getColumnIndex(marketListDB.MENU_NAME));
                      String quantity=cursor.getString(cursor.getColumnIndex(marketListDB.MENU_QUANTITY));
                      dailyMarketApi.insertIntoTheSenderTable("01727123374",dilogMembersViewHolder.member_number.getText().toString(),""+date+","+time,item_name,quantity,"","0")
                              .enqueue(new Callback<check1>() {
                                  @Override
                                  public void onResponse(Call<check1> call, Response<check1> response) {
                                      check1 c=response.body();

                                      marketListDB.delete_market_list(id);

                                      FirebaseDatabase.getInstance().getReference("Token").child("+88"+dilogMembersViewHolder.member_number.getText().toString())
                                              .addValueEventListener(new ValueEventListener() {
                                                  @Override
                                                  public void onDataChange(DataSnapshot dataSnapshot) {
                                                      Token token =dataSnapshot.getValue(Token.class);
                                                      token_key=token.getToken();
                                                      Log.i("TOKEN_ID",token_key);

                                                      Toast.makeText(context, "Token:==="+token_key, Toast.LENGTH_SHORT).show();


                                                  }

                                                  @Override
                                                  public void onCancelled(DatabaseError databaseError) {

                                                  }
                                              });

                                      if (c.getResult().equals("ok")){
                                          String title="New  Request";
                                          String body="You have a new bazar list request";
                                          Notification data=new Notification(title,body);
                                          Sender content=new Sender(token_key,data);

                                          fcMservice.sendMessage(content).enqueue(new Callback<FCMResponse>() {
                                              @Override
                                              public void onResponse(Call<FCMResponse> call, Response<FCMResponse> response) {
                                                  if (response.body().success==1){
                                                      Toast.makeText(context, "Sended The Request successfully", Toast.LENGTH_SHORT).show();
                                                  }
                                              }

                                              @Override
                                              public void onFailure(Call<FCMResponse> call, Throwable t) {
                                                  Toast.makeText(context, "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
                                              }
                                          });

                                          AlertDialog.Builder alrt=new AlertDialog.Builder(context);
                                          alrt.setMessage("You successfully send to your member.To see the list Please press ");
                                          alrt.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {
                                                  dialog.dismiss();
                                                  Common.value1=1;

                                                  ((Activity)context).startActivity(new Intent(context, Market.class));

                                              }
                                          });
                                          alrt.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {
                                                  Common.value1=2;
                                                  ((Activity)context).startActivity(new Intent(context, Market.class));
                                                  dialog.dismiss();


                                              }
                                          });

                                          alrt.show();
                                      }
                                  }

                                  @Override
                                  public void onFailure(Call<check1> call, Throwable t) {
                                      Log.i("API",t.getMessage());
                                      Toast.makeText(context, "Field "+t.getMessage(), Toast.LENGTH_SHORT).show();
                                  }
                              });

                  }


              }
          });

    }

    @Override
    public int getItemCount() {
        return memberNumberList.size();
    }

    public class DilogMembersViewHolder extends RecyclerView.ViewHolder {
        TextView member_number;
        ImageView send;
        public DilogMembersViewHolder(@NonNull View itemView) {
            super(itemView);

            member_number=itemView.findViewById(R.id.number);
            send=itemView.findViewById(R.id.send);
        }
    }
}
