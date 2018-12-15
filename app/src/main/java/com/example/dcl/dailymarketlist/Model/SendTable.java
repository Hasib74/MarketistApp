package com.example.dcl.dailymarketlist.Model;

public class SendTable {
    private String receiveid;
    private String dateandtime;
    private String itemname;
    private String quantity;
    private String price;
    private String confrom;

    public SendTable() {
    }

    public SendTable(String receiveid, String dateandtime, String itemname, String quantity, String price, String confrom) {
        this.receiveid = receiveid;
        this.dateandtime = dateandtime;
        this.itemname = itemname;
        this.quantity = quantity;
        this.price = price;
        this.confrom = confrom;
    }

    public String getReceiveid() {
        return receiveid;
    }

    public void setReceiveid(String receiveid) {
        this.receiveid = receiveid;
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
