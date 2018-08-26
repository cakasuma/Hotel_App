package com.example.amam.order_reservation;

/**
 * Created by amam on 23/8/2018.
 */

public class Item {
    private String Category, Description, Image, Name, Price, Type;

    public Item() {
    }

    public Item(String category, String description, String image, String name, String price, String type) {
        Category = category;
        Description = description;
        Image = image;
        Name = name;
        Price = price;
        Type = type;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
