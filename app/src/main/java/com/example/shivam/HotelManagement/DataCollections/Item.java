package com.example.shivam.HotelManagement.DataCollections;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shivam on 2/11/17.
 */

public class Item {
    HashMap<String, String> price;

    public Item() {
        this.price.put("Tshirt", "10");
        this.price.put("Jeans", "10");
        this.price.put("Shirt", "10");
        this.price.put("Trouser", "10");
        this.price.put("Shorts", "10");
        this.price.put("Towel", "10");
        this.price.put("Veg", "10");
//        this.price.put("Tshirt", "10");
//        this.price.put("Tshirt", "10");
//        this.price.put("Tshirt", "10");
//        this.price.put("Tshirt", "10");
//        this.price.put("Tshirt", "10");
//        this.price.put("Tshirt", "10");
//        this.price.put("Tshirt", "10");
    }

    public HashMap<String, String> getPrice() {

        return price;
    }

    public void setPrice(HashMap<String, String> price) {
        this.price = price;
    }
}
