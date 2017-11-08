package com.example.shivam.HotelManagement.DataCollections;

import java.util.ArrayList;

/**
 * Created by shivam on 1/11/17.
 */

public class Bill {
    private ArrayList<Service> services = new ArrayList<>();
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }
}
