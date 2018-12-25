package com.example.dcl.dailymarketlist.Retrofit;

import com.example.dcl.dailymarketlist.Model.Check;
import com.example.dcl.dailymarketlist.Model.ReceivedData;
import com.example.dcl.dailymarketlist.Model.SendTable;
import com.example.dcl.dailymarketlist.Model.ShowSendBazarListHelper;
import com.example.dcl.dailymarketlist.Model.check1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DailyMarketApi {

    @FormUrlEncoded
    @POST("create_table.php")
    Call<Check> create_table(@Field("number") String number);

    @FormUrlEncoded
    @POST("insertintoconfromtable.php")
    Call<check1> insertIntoCompletedTable(@Field("mynumber") String number,
                                          @Field("dateandtime") String date_and_time,
                                          @Field("itemname") String item_name,
                                          @Field("quantity") String quantity,
                                          @Field("price") String price);
    @FormUrlEncoded
    @POST("insertIntoreceivertable.php")
    Call<check1> insertIntoTheReceiverTable(@Field("mynumber") String number,
                                          @Field("senderNumber") String sender_number,
                                          @Field("dateandtime") String date_and_time,
                                          @Field("itemname") String item_name,
                                          @Field("quantity") String quantity,
                                          @Field("price") String price,
                                          @Field("confromstatus") String confrom_status);
    @FormUrlEncoded
    @POST("inseritothrsendertable.php")
    Call<String> insertIntoTheSenderTable   (@Field("mynumber") String number,
                                             @Field("receiverNumber") String sender_number,
                                             @Field("dateandtime") String date_and_time,
                                             @Field("itemname") String item_name,
                                             @Field("quantity") String quantity,
                                             @Field("price") String price,
                                             @Field("confromstatus") String confrom_status);

    @FormUrlEncoded
    @POST("readdatafromsendtable.php")
    Call<List<SendTable>>readDataFromSendTable(@Field("mynumber") String number);


    @FormUrlEncoded
    @POST("readShowSendBazarList.php")
    Call<List<ShowSendBazarListHelper>> readShowSendBazarList  (@Field("number") String number, @Field("dateandtime") String dateAndtime);

    @FormUrlEncoded
    @POST("readAllDataFromReceivedTable.php")
    Call<List<ReceivedData>>readDataFromReceivedTable(@Field("mynumber") String number);

    @FormUrlEncoded
    @POST("readShowReceivedBazarList.php")
    Call<List<ShowSendBazarListHelper>> readShowReceivedBazarList  (@Field("number") String number, @Field("dateandtime") String dateAndtime);





}
