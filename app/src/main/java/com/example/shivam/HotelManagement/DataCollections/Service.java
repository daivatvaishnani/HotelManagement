package com.example.shivam.HotelManagement.DataCollections;

import java.util.ArrayList;

/**
 * Created by shivam on 1/11/17.
 */

public class Service {
    private String roomID;
    private String serviceType;
    private ArrayList<Item> items = new ArrayList<>();
    private String serviceAmount = "0";

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Service(String roomID, String serviceType) {
        this.roomID = roomID;
        this.serviceType = serviceType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public void addItemToService(String itemName, String itemPrice, String itemType, String itemQuantity) {
        Item i = new Item(itemName, itemPrice, itemType);
        i.setItemQuantity(itemQuantity);
        this.items.add(i);
        serviceAmount = Integer.toString(Integer.parseInt(serviceAmount)
                                            + Integer.parseInt(itemPrice)*Integer.parseInt(itemQuantity)
                                        );
    }

    public void addItemToService(Item i, String itemQuantity) {
        i.setItemQuantity(itemQuantity);
        this.items.add(i);
        serviceAmount = Integer.toString(Integer.parseInt(serviceAmount)
                                            + Integer.parseInt(i.getItemPrice())*Integer.parseInt(itemQuantity)
                                        );
    }

}
