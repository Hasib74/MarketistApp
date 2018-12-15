package com.example.dcl.dailymarketlist.Model;

public class Menu {
    private String info_id;
    private String unic_id;
    private  String menu_name;
    private String menu_quantity;
    private String price;
    private String confrom_product;

    public String getConfrom_product() {
        return confrom_product;
    }

    public void setConfrom_product(String confrom_product) {
        this.confrom_product = confrom_product;
    }

    public Menu(String info_id, String unic_id, String menu_name, String menu_quantity, String price, String confrom_product) {
        this.info_id = info_id;
        this.unic_id = unic_id;
        this.menu_name = menu_name;
        this.menu_quantity = menu_quantity;
        this.price = price;
        this.confrom_product = confrom_product;
    }

    public Menu(String price) {
        this.price = price;
    }

    public Menu(String info_id, String unic_id, String menu_name, String menu_quantity, String price) {
        this.info_id = info_id;
        this.unic_id = unic_id;
        this.menu_name = menu_name;
        this.menu_quantity = menu_quantity;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Menu(String info_id, String unic_id, String menu_name) {
        this.info_id = info_id;
        this.unic_id = unic_id;
        this.menu_name = menu_name;
    }

    public Menu(String info_id, String unic_id, String menu_name, String menu_quantity) {
        this.info_id = info_id;
        this.unic_id = unic_id;
        this.menu_name = menu_name;
        this.menu_quantity = menu_quantity;
    }

    public Menu() {
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

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_quantity() {
        return menu_quantity;
    }

    public void setMenu_quantity(String menu_quantity) {
        this.menu_quantity = menu_quantity;
    }
}
