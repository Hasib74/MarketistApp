package com.example.dcl.dailymarketlist.Model;

public class CompletedBaxarListMenuModel {

    private String info_id;
    private  String unic_id;
    private String menuName;
    private String quantity;
    private  String price;
    private  String complete_status;
    private  String insert_into_the_mysql;

    public String getComplete_status() {
        return complete_status;
    }

    public void setComplete_status(String complete_status) {
        this.complete_status = complete_status;
    }

    public String getInsert_into_the_mysql() {
        return insert_into_the_mysql;
    }

    public void setInsert_into_the_mysql(String insert_into_the_mysql) {
        this.insert_into_the_mysql = insert_into_the_mysql;
    }

    public CompletedBaxarListMenuModel(String info_id, String unic_id, String menuName, String quantity, String price, String complete_status, String insert_into_the_mysql) {
        this.info_id = info_id;
        this.unic_id = unic_id;
        this.menuName = menuName;
        this.quantity = quantity;
        this.price = price;
        this.complete_status = complete_status;
        this.insert_into_the_mysql = insert_into_the_mysql;
    }

    public CompletedBaxarListMenuModel(String menuName, String quantity, String price) {
        this.menuName = menuName;
        this.quantity = quantity;
        this.price = price;
    }

    public CompletedBaxarListMenuModel(String info_id, String unic_id, String menuName, String quantity, String price) {
        this.info_id = info_id;
        this.unic_id = unic_id;
        this.menuName = menuName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getUnic_id() {
        return unic_id;
    }

    public void setUnic_id(String unic_id) {
        this.unic_id = unic_id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
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
