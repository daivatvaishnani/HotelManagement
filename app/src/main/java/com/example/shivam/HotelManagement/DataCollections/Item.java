package com.example.shivam.HotelManagement.DataCollections;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shivam on 2/11/17.
 */

public class Item {

    private String itemName;
    private String itemPrice;
    private String itemType;
    private String itemQuantity = "0";

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Item(String itemName, String itemPrice, String itemType) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
    }

}
