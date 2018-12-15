package com.example.dcl.dailymarketlist.Model;

public class CompletedBazarList {
    private  String key_id;
    private String date;
    private  String time;

    public CompletedBazarList(String key_id, String date, String time) {
        this.key_id = key_id;
        this.date = date;
        this.time = time;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
