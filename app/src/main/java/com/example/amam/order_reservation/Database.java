package com.example.amam.order_reservation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amam on 25/8/2018.
 */

public class Database extends SQLiteAssetHelper{

    private final static String DB_NAME = "hmsapu.db";
    private final static int DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCarts(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlselect = {"ItemID", "ItemName", "Quantity", "Price"};
        String sqltable = "OrderDetail";

        qb.setTables(sqltable);
        Cursor c = qb.query(db, sqlselect,null,null,null,null,null);
        final List<Order> result = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                result.add(new Order(c.getString(c.getColumnIndex("ItemID")),
                        c.getString(c.getColumnIndex("ItemName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price"))
                ));
            }while (c.moveToNext());
        }
        return result;
    }

    public void addToCart(Order order){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ItemID, ItemName, Quantity, Price) VALUES ('%s','%s','%s','%s');",
                order.getItemID(),order.getItemName(),order.getQuantity(),order.getPrice());
        db.execSQL(query);
    }

    public void cleanCart(){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }
}
