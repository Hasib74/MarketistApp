package com.example.dcl.dailymarketlist.Interface;

import com.example.dcl.dailymarketlist.Model.FCMResponse;
import com.example.dcl.dailymarketlist.Model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FCMservice {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AIzaSyAqSPMouIkKlFQZ8ucCiYo64Gxp_a4IPxY"
    })
    @POST("fcm/send")
    Call<FCMResponse> sendMessage(@Body Sender body);
}
