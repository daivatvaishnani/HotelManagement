package com.example.shivam.HotelManagement.DataCollections;

import java.util.Date;

/**
 * Created by lonewolf on 12/11/17.
 */

public class Room {
    private String roomID;
    private String roomType;
    private boolean hasAdvancedBooking = false;
    private Date bookedFromDate;
    private Date bookedTillDate;

    public Date getBookedFromDate() {
        return bookedFromDate;
    }

    public void setBookedFromDate(Date bookedFromDate) {
        this.bookedFromDate = bookedFromDate;
    }

    public Date getBookedTillDate() {
        return bookedTillDate;
    }

    public void setBookedTillDate(Date bookedTillDate) {
        this.bookedTillDate = bookedTillDate;
    }

    public Room(String roomID, String roomType) {
        this.roomID = roomID;
        this.roomType = roomType;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isAvailabeFor(Date chekInDate, Date checkOutDate) {
        if (hasAdvancedBooking) {
            if (chekInDate.after(bookedTillDate) || checkOutDate.before(bookedFromDate)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }
}