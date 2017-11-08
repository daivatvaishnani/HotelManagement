package com.example.shivam.HotelManagement.DataCollections;

import android.widget.Toast;

import com.example.shivam.HotelManagement.Activity.MainActivity;
import com.example.shivam.HotelManagement.Activity.RegisterActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivam on 1/11/17.
 */

public class Database
{
    private ArrayList<User> users = new ArrayList<>();
//    public List<User> users = new ArrayList<>();
    private String singleRoomPrice, doubleRoomPrice, deluxeRoomPrice;
    private ArrayList<Item> items = new ArrayList<>();

    private Session activeSession = new Session(null);

    public Session getActiveSession() {
        return activeSession;
    }

    public void setActiveSession(Session activeSession) {
        this.activeSession = activeSession;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public String getDoubleRoomPrice() {
        return doubleRoomPrice;
    }

    public void setDoubleRoomPrice(String doubleRoomPrice) {
        this.doubleRoomPrice = doubleRoomPrice;
    }

    public String getDeluxeRoomPrice() {
        return deluxeRoomPrice;
    }

    public void setDeluxeRoomPrice(String deluxeRoomPrice) {
        this.deluxeRoomPrice = deluxeRoomPrice;
    }

    public String getSingleRoomPrice() {
        return singleRoomPrice;
    }

    public void setSingleRoomPrice(String singleRoomPrice) {
        this.singleRoomPrice = singleRoomPrice;
    }

    // GETS LIST OF EMP WITH GIVEN UAL
    public ArrayList<User> getUsers(String ual) {
        ArrayList<User> userlist = new ArrayList<>();
        for(User u : users) {
            if(u.getUserAccessLevel().equals(ual)) {
                userlist.add(u);
            }
        }
        return userlist;
    }

    public User getUser(String emailID) {
        for(User u : users) {
            if (u.getEmailId().equals(emailID)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<User> getUser(String un, String dump) {
        ArrayList<User> userlist = new ArrayList<>();
        for(User u : users) {
            if(u.getUserName().equals(un)) {
                userlist.add(u);
            }
        }
        return userlist;
    }

    public void addUser(User u) {
        this.users.add(u);
    }

    public int registerUser(String e, String p, String ual, String un, String pn) {
        for(User u : users) {
            if(u.getEmailId().equals(e)) {
                return 0;
            }
        }
        User us = new User(e, p, ual, un, pn);
        addUser(us);
        return 1;
    }

    //serviceType : Laundry / Food
    public ArrayList<Item> getItems(String serviceType) {
        ArrayList<Item> itemList = new ArrayList<>();
        for(Item i : this.items) {
            if(i.getItemType().equals(serviceType)) {
                itemList.add(i);
            }
        }
        return itemList;
    }

    public Database()
    {
        // ADD USERS
        this.users.add(new User("manager@gmail.com","manager","1","manager","123123123123"));
        this.users.add(new User("supervisor1@gmail.com","supervisor1","2","supervisor1","123123"));
        this.users.add(new User("supervisor2@gmail.com","supervisor2","2","supervisor2","213123"));
        this.users.add(new User("supervisor3@gmail.com","supervisor3","2","supervisor3","1223231123"));
        this.users.add(new User("guest1@gmail.com","guest1","3","guest1","123123455"));
        this.users.add(new User("guest2@gmail.com","guest2","3","guest2","1212433513123"));
        this.users.add(new User("guest3@gmail.com","guest3","3","guest3","123123435532131"));
        this.users.add(new User("guest4@gmail.com","guest4","3","guest4","1231232452131"));
        this.users.add(new User("fds1@gmail.com","fds1","4","fds1","12323521312312"));
        this.users.add(new User("fds2@gmail.com","fds2","4","fds2","123241111231123"));
        this.users.add(new User("fds3@gmail.com","fds3","4","fds3","123213231231"));
        // ADD ITEMS
        this.items.add(new Item("Shirt", "20", "Laundry"));
        this.items.add(new Item("TShirt", "10", "Laundry"));
        this.items.add(new Item("Trousers", "15", "Laundry"));
        this.items.add(new Item("Jeans", "15", "Laundry"));
        this.items.add(new Item("Shorts", "10", "Laundry"));
        this.items.add(new Item("Towel", "20", "Laundry"));
        this.items.add(new Item("Sweater", "20", "Laundry"));
        this.items.add(new Item("Burger", "50", "Food"));
        this.items.add(new Item("Chips", "40", "Food"));
        this.items.add(new Item("Sandwich", "50", "Food"));
        this.items.add(new Item("Veg-Thali", "100", "Food"));
        this.items.add(new Item("NonVeg-Thali", "200", "Food"));
        // SET PRICES
        setSingleRoomPrice("NULL");
        setDoubleRoomPrice("NULL");
        setDeluxeRoomPrice("NULL");
    }
    public void initialize()
    {
        // ADD USERS


        // SET PRICES

        // CHUTIYA BANANE K LIYE DAALA H
    }
}
