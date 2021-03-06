package com.example.shivam.HotelManagement.DataCollections;

import java.util.ArrayList;

/**
 * Created by shivam on 1/11/17.
 */

public class Bill {
    private ArrayList<Service> services = new ArrayList<>();
    private String billAmount = "0";

    public String getBillAmount() {
        int amt = 0;
        for(Service s : services) {
            amt += Integer.parseInt(s.getServiceAmount());
        }
        billAmount = Integer.toString(amt);
        return billAmount;
    }

    public Service getLastService() {
        if(services.isEmpty()) {
            return null;
        }
        else {
            return services.get(services.size() - 1);
        }
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

    public void addServiceToBill(Service s) {
        this.services.add(s);
        int amount = Integer.parseInt(s.getServiceAmount());
        String billAmt = Integer.toString(Integer.parseInt(billAmount) + amount);
        this.billAmount = billAmt;
    }

    public void addServiceToBill(String roomId, String roomType, String serviceType) {
        this.services.add(new Service(roomId, roomType, serviceType));
    }
}
