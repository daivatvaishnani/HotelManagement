package com.example.shivam.HotelManagement.DataCollections;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shivam on 2/11/17.
 */

public class Item {

    String itemName;
    String itemPrice;

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

    public Item(String itemName, String itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}
