package com.example.shivam.HotelManagement.DataCollections;

/**
 * Created by lonewolf on 12/11/17.
 */

public class Room {
    private String roomID;
    private String roomType;

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
}
