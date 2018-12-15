package com.example.dcl.dailymarketlist.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public  static Retrofit retrofit=null;
    public  static Retrofit getClient(String Baseurl){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
