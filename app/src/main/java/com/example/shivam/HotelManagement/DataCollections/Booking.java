package com.example.shivam.HotelManagement.DataCollections;

import java.util.ArrayList;

/**
 * Created by shivam on 1/11/17.
 */

public class Booking {
    private ArrayList<Room> rooms = new ArrayList<>();
    private Bill bill = new Bill();

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

}
