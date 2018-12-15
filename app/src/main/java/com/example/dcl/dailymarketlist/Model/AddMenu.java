package com.example.dcl.dailymarketlist.Model;

public class AddMenu {
    private String menu_name;
    private String quantity;

    public AddMenu() {
    }

    public String getQuntity_marks() {
        return quntity_marks;
    }

    public AddMenu(String quntity_marks) {
        this.quntity_marks = quntity_marks;
    }

    public void setQuntity_marks(String quntity_marks) {
        this.quntity_marks = quntity_marks;
    }

    private String quntity_marks;


    public AddMenu(String menu_name, String quantity, String quntity_marks) {
        this.menu_name = menu_name;
        this.quantity = quantity;
        this.quntity_marks = quntity_marks;
    }

    public AddMenu(String menu_name, String quantity)
    {
        this.menu_name = menu_name;
        this.quantity = quantity;
    }

    public String getMenu_name()
    {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
