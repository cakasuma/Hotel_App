package com.example.amam.order_reservation;

import java.util.List;

/**
 * Created by amam on 26/8/2018.
 */

public class Request {
    private String userID, RoomNo, total, datetime;
    private List<Order> items;

    public Request() {
    }

    public Request(String userID, String roomNo, String total, String datetime, List<Order> items) {
        this.userID = userID;
        RoomNo = roomNo;
        this.total = total;
        this.datetime = datetime;
        this.items = items;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public List<Order> getItems() {
        return items;
    }

    public void setItems(List<Order> items) {
        this.items = items;
    }
}
