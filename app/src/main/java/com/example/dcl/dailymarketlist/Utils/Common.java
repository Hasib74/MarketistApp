package com.example.dcl.dailymarketlist.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.dcl.dailymarketlist.Interface.FCMservice;
import com.example.dcl.dailymarketlist.Model.CompleteMarket;
import com.example.dcl.dailymarketlist.Model.Member;
import com.example.dcl.dailymarketlist.Model.MemberNumber;
import com.example.dcl.dailymarketlist.Model.User;
import com.example.dcl.dailymarketlist.Retrofit.DailyMarketApi;
import com.example.dcl.dailymarketlist.Retrofit.RetrofitClient;
import com.example.dcl.dailymarketlist.Service.FCMclient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Common {
    private Context context;

    public Common(Context context) {
        this.context = context;
    }

    public static final  String BASE_URL="http://192.168.0.109/DailyMarket/";
    public  static  final String fcmURL="https://fcm.googleapis.com/";
    public static FCMservice getFCMService()
    {
        return FCMclient.getClient(fcmURL).create(FCMservice.class);
    }


    public static DailyMarketApi getApi(){
         return RetrofitClient.getClient(BASE_URL).create(DailyMarketApi.class);
     }
     public static  String phone_number;

    public static String getTime(){
        Date date = new Date();
        String strDateFormat = "HH:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        return sdf.format(date);
    }

    public static List<CompleteMarket> completeMarketsList;

    public  static  int value=0;
    public  static int  value1=0;
    public static List<String> member_list;

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static List<CompleteMarket> completeMarketList;

    public  static  List<Member> memberList;
    public static  List<User> userList;
    public static  List<String> contract_number_list;
    public  static  int load=0;


    public static List<MemberNumber> read_all_members() {
         List<MemberNumber> members_info_list=new ArrayList<>();
        for (int i = 0; i <userList.size(); i++) {
            for (int j = 0; j <contract_number_list.size(); j++) {
                String registerNumber = userList.get(i).getNumber();
                if (registerNumber.equals(contract_number_list.get(j))) {
                    MemberNumber member=new MemberNumber(contract_number_list.get(j));
                    members_info_list.add(member);
                }
            }
        }
        return members_info_list;
    }
}
