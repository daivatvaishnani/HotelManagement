package com.example.shivam.HotelManagement.DataCollections;

import java.util.ArrayList;

/**
 * Created by shivam on 1/11/17.
 */

public class Booking {
    private String bookingID;
    private ArrayList<Room> rooms = new ArrayList<>();
    private Bill bill = new Bill();

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRooms(ArrayList<Room> roomsList) {
        this.rooms.addAll(roomsList);
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

}
