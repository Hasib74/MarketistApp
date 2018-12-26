package com.example.dcl.dailymarketlist.Model;

public class ReceivedData {
    //"senderid":"8801823585800","dateandtime":"aaaaaaaaaaaa","itemname":"ssssssssssss","quantity":"dddddddddd","price":"dffffffffff","confrom":"fffffffffff"
    private  String senderid;
    private  String dateandtime;
    private  String itemname;
    private String quantity;
    private  String price;
    private String confrom;


    public ReceivedData(String senderid, String dateandtime, String itemname, String quantity, String price, String confrom) {
        this.senderid = senderid;
        this.dateandtime = dateandtime;
        this.itemname = itemname;
        this.quantity = quantity;
        this.price = price;
        this.confrom = confrom;
    }

    public String getSenderid() {
        return senderid;

    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getDateandtime() {
        return dateandtime;
    }

    public void setDateandtime(String dateandtime) {
        this.dateandtime = dateandtime;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
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

    public String getConfrom() {
        return confrom;
    }

    public void setConfrom(String confrom) {
        this.confrom = confrom;
    }
}
