package com.example.dcl.dailymarketlist.Model;

public class CompleteMySql {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String date;
    private String time;
    private String itemName;
    private String quantity;
    private String price;

    public CompleteMySql(String id,String date, String time, String itemName, String quantity, String price) {
        this.date = date;
        this.time = time;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.id=id;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
