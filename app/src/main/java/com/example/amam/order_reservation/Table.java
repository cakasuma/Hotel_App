package com.example.amam.order_reservation;

/**
 * Created by amam on 27/8/2018.
 */

public class Table {
    private String userId, dateReserve, timeReserve, guestQuantity, contactPerson, roomNo;

    public Table() {
    }

    public Table(String userId, String dateReserve, String timeReserve, String guestQuantity, String contactPerson, String roomNo) {
        this.userId = userId;
        this.dateReserve = dateReserve;
        this.timeReserve = timeReserve;
        this.guestQuantity = guestQuantity;
        this.contactPerson = contactPerson;
        this.roomNo = roomNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDateReserve() {
        return dateReserve;
    }

    public void setDateReserve(String dateReserve) {
        this.dateReserve = dateReserve;
    }

    public String getTimeReserve() {
        return timeReserve;
    }

    public void setTimeReserve(String timeReserve) {
        this.timeReserve = timeReserve;
    }

    public String getGuestQuantity() {
        return guestQuantity;
    }

    public void setGuestQuantity(String guestQuantity) {
        this.guestQuantity = guestQuantity;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
}
