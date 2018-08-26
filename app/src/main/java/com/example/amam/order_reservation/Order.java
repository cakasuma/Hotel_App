package com.example.amam.order_reservation;

/**
 * Created by amam on 25/8/2018.
 */

public class Order {
    private String ItemID, ItemName, Quantity, Price;

    public Order() {
    }

    public Order(String itemID, String itemName, String quantity, String price) {
        ItemID = itemID;
        ItemName = itemName;
        Quantity = quantity;
        Price = price;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setProductId(String itemID) {
        ItemID = itemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setProductName(String itemName) {
        ItemName = itemName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
