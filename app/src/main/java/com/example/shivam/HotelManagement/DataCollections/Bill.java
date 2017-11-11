package com.example.shivam.HotelManagement.DataCollections;

import java.util.ArrayList;

/**
 * Created by shivam on 1/11/17.
 */

public class Bill {
    private ArrayList<Service> services = new ArrayList<>();
    private String billAmount;

    public String calculateBillAmount() {
        int amt = 0;
        for(Service s : services) {
            amt += Integer.parseInt(s.getServiceAmount());
        }
        return Integer.toString(amt);
    }

    public Bill() {
        this.billAmount = "0";
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String amount) {
        this.billAmount = amount;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }
}
