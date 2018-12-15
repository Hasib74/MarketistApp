package com.example.dcl.dailymarketlist.Model;

public class OwnBazar {
    private String id;
    private String date;
    private String time;
    private  String total;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public OwnBazar(String id, String date, String time, String total) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.total = total;
    }

    public OwnBazar(String id, String date, String time) {
        this.id = id;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
