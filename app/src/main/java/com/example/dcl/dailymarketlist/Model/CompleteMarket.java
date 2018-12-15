package com.example.dcl.dailymarketlist.Model;

public class CompleteMarket {
    private  String id;
    private  String date;
    private String time;
    private String completed_status;
    private String inserted_into_mysql;

    public CompleteMarket(String id, String date, String time, String completed_status, String inserted_into_mysql) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.completed_status = completed_status;
        this.inserted_into_mysql = inserted_into_mysql;
    }

    public CompleteMarket(String id, String date, String time) {
        this.id = id;
        this.date = date;
        this.time = time;
    }

    public String getCompleted_status() {
        return completed_status;
    }

    public void setCompleted_status(String completed_status) {
        this.completed_status = completed_status;
    }

    public String getInserted_into_mysql() {
        return inserted_into_mysql;
    }

    public void setInserted_into_mysql(String inserted_into_mysql) {
        this.inserted_into_mysql = inserted_into_mysql;
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
