package com.example.shivam.HotelManagement.DataCollections;

import android.widget.Toast;

import com.example.shivam.HotelManagement.Activity.MainActivity;
import com.example.shivam.HotelManagement.Activity.RegisterActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by shivam on 1/11/17.
 */

public class Database
{
    private ArrayList<User> users = new ArrayList<>();
//    public List<User> users = new ArrayList<>();
    private String singleRoomPrice, doubleRoomPrice, deluxeRoomPrice;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();

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

    public ArrayList<User> getUsersUnder(String ual) {
        int ulevel = parseInt(ual);
        ArrayList<User> userlist = new ArrayList<>();
        for(User u : users) {
            int level = parseInt(u.getUserAccessLevel());
            if(ulevel == 1) {
                if(level == 2 || level == 4) {
                    userlist.add(u);
                }
            }
            if(ulevel == 2) {
                if(level == 5) {
                    userlist.add(u);
                }
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

    public User getUser(String un, String dump) {
        for(User u : users) {
            if (u.getUserName().equals(un)) {
                return u;
            }
        }
        return null;
    }

    public void addUser(User u) {
        this.users.add(u);
    }
    
    public void setActiveUserUserName(String newUserName) {
        String username = activeSession.getActiveUser().getUserName();
        for(User u : users) {
            if(u.getUserName().equals(username)) {
                u.setUserName(newUserName);
            }
        }
        activeSession.getActiveUser().setUserName(newUserName);
    }

    public void setActiveUserEmailID(String newUserEmail) {
        String username = activeSession.getActiveUser().getUserName();
        for(User u : users) {
            if(u.getUserName().equals(username)) {
                u.setEmailId(newUserEmail);
            }
        }
        activeSession.getActiveUser().setEmailId(newUserEmail);
    }

    public void setActiveUserPwd(String newUserPwd) {
        String username = activeSession.getActiveUser().getUserName();
        for(User u : users) {
            if(u.getUserName().equals(username)) {
                u.setPwd(newUserPwd);
            }
        }
        activeSession.getActiveUser().setPwd(newUserPwd);
    }

    public void setActiveUserPhno(String newUserPhno) {
        String username = activeSession.getActiveUser().getUserName();
        for(User u : users) {
            if(u.getUserName().equals(username)) {
                u.setPhoneNo(newUserPhno);
            }
        }
        activeSession.getActiveUser().setPhoneNo(newUserPhno);
    }

    public int registerUser(String e, String p, String ual, String un, String pn) {
        for(User u : users) {
            if(u.getEmailId().equals(e) || u.getUserName().equals(un)) {
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

    public Room getRoom(String roomId, String roomType) {
        for(Room r : rooms) {
            if(r.getRoomID().equals(roomId) && r.getRoomType().equals(roomType)) {
                return r;
            }
        }
        return null;
    }

    private ArrayList<Room> trybook(Date checkInDate, Date checkOutDate, ArrayList<Room> availableRooms, String noOfRoomsRequired, String roomType) {
        ArrayList<Room> roomsbooked = new ArrayList<>();
        int noRequired = parseInt(noOfRoomsRequired);
        if(availableRooms.size() > noRequired) {
            int booked = 0;
            while(booked < noRequired) {
                this.getRoom(availableRooms.get(booked).getRoomID(), roomType).setBookedFromDate(checkInDate);
                this.getRoom(availableRooms.get(booked).getRoomID(), roomType).setBookedTillDate(checkOutDate);
                this.getRoom(availableRooms.get(booked).getRoomID(), roomType).setHasAdvancedBooking(true);
                roomsbooked.add(availableRooms.get(booked));
                booked = booked + 1;
            }
        }
        return roomsbooked;
    }

    public ArrayList<Room> getAvailableRooms(Date checkInDate, Date checkOutDate, String roomType) {
        ArrayList<Room> returnList = new ArrayList<>();
        for(Room r : rooms) {
            if(r.getRoomType().equals(roomType) && r.isAvailabeFor(checkInDate, checkOutDate)) {
                returnList.add(r);
            }
        }
        return returnList;
    }

    public int checkAvailablity(Date checkInDate, Date checkOutDate, String noOfSingle, String noOfDouble, String noOfDeluxe) {
        ArrayList<Room> availableSingle = getAvailableRooms(checkInDate, checkOutDate, "1");
        if(availableSingle.size() < parseInt(noOfSingle)) {
            return 1;
        }
        ArrayList<Room> availableDouble = getAvailableRooms(checkInDate, checkOutDate, "2");
        if(availableDouble.size() < parseInt(noOfDouble)) {
            return 2;
        }
        ArrayList<Room> availableDeluxe = getAvailableRooms(checkInDate, checkOutDate, "3");
        if(availableDeluxe.size() < parseInt(noOfDeluxe)) {
            return 3;
        }
        return 0;
    }

    public void doBooking(String username, Date checkInDate, Date checkOutDate, String noOfSingle, String noOfDouble, String noOfDeluxe) {
        int status = checkAvailablity(checkInDate, checkOutDate, noOfSingle, noOfDouble, noOfDeluxe);
        if(status == 0) {
            ArrayList<Room> availableSingle = getAvailableRooms(checkInDate, checkOutDate, "1");
            ArrayList<Room> availableDouble = getAvailableRooms(checkInDate, checkOutDate, "2");
            ArrayList<Room> availableDeluxe = getAvailableRooms(checkInDate, checkOutDate, "3");

            ArrayList<Room> singleRooms = trybook(checkInDate, checkOutDate, availableSingle, noOfSingle, "1");
            ArrayList<Room> doubleRooms = trybook(checkInDate, checkOutDate, availableDouble, noOfDouble, "2");
            ArrayList<Room> deluxeRooms = trybook(checkInDate, checkOutDate, availableDeluxe, noOfDeluxe, "3");

            ArrayList<Room> bookedRooms = new ArrayList<>();
            bookedRooms.addAll(singleRooms);
            bookedRooms.addAll(doubleRooms);
            bookedRooms.addAll(deluxeRooms);
            Booking booking = new Booking();
            User u = getUser(username, " ");
            booking.setBookingID(Integer.toString(u.getBookings().size() + 1));
            booking.addRooms(bookedRooms);
            this.getUser(username, " ").getBookings().add(booking);
        }
    }

    public User getUserInRoom(String roomID, String roomType) {
        for(User u : users) {
            if(u.hasABooking()) {
                for(Booking b : u.getBookings()) {
                    for(Room r : b.getRooms()) {
                        if(r.getRoomType().equals(roomType) && r.getRoomID().equals(roomID)) {
                            return u;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void AddServiceToBill (String roomId, String roomType, String serviceType, ArrayList<Item> serviceItems, ArrayList<String> quantities) throws NullPointerException {
        Service s = new Service(roomId, roomType, serviceType);
        for(int i = 0; i < serviceItems.size(); ++i) {
            if(Integer.parseInt(quantities.get(i)) > 0) {
                s.addItemToService(serviceItems.get(i), quantities.get(i));
            }
        }
        this.getUserInRoom(roomId, roomType).getBookings().get(0).getBill().addServiceToBill(s);
    }

    public Database()
    {
        // ADD USERS
        this.users.add(new User("manager@gmail.com","manager","1","manager","8696078312"));
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
        this.users.add(new User("employee1@gmail.com", "employee1", "5", "employee1", "1231231232"));
        this.users.add(new User("employee2@gmail.com", "employee2", "5", "employee2", "1231231232"));
        this.users.add(new User("employee3@gmail.com", "employee3", "5", "employee3", "1231231232"));
        this.users.add(new User("employee4@gmail.com", "employee4", "5", "employee4", "1231231232"));
        this.users.add(new User("employee5@gmail.com", "employee5", "5", "employee5", "1231231232"));
        this.users.add(new User("employee6@gmail.com", "employee6", "5", "employee6", "1231231232"));
        this.users.add(new User("employee7@gmail.com", "employee7", "5", "employee7", "1231231232"));
        this.users.add(new User("employee8@gmail.com", "employee8", "5", "employee8", "1231231232"));
        this.users.add(new User("employee9@gmail.com", "employee9", "5", "employee9", "1231231232"));
        this.users.add(new User("employee10@gmail.com", "employee10", "5", "employee10", "1231231232"));
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
        //ADD ROOMS
        this.rooms.add(new Room("1", "1"));
        this.rooms.add(new Room("2", "1"));
        this.rooms.add(new Room("3", "1"));
        this.rooms.add(new Room("4", "1"));
        this.rooms.add(new Room("5", "1"));
        this.rooms.add(new Room("6", "1"));
        this.rooms.add(new Room("7", "1"));
        this.rooms.add(new Room("8", "1"));
        this.rooms.add(new Room("9", "1"));
        this.rooms.add(new Room("10", "1"));
        this.rooms.add(new Room("1", "2"));
        this.rooms.add(new Room("2", "2"));
        this.rooms.add(new Room("3", "2"));
        this.rooms.add(new Room("4", "2"));
        this.rooms.add(new Room("5", "2"));
        this.rooms.add(new Room("6", "2"));
        this.rooms.add(new Room("7", "2"));
        this.rooms.add(new Room("8", "2"));
        this.rooms.add(new Room("9", "2"));
        this.rooms.add(new Room("10", "2"));
        this.rooms.add(new Room("1", "3"));
        this.rooms.add(new Room("2", "3"));
        this.rooms.add(new Room("3", "3"));
        this.rooms.add(new Room("4", "3"));
        this.rooms.add(new Room("5", "3"));
        this.rooms.add(new Room("6", "3"));
        this.rooms.add(new Room("7", "3"));
        this.rooms.add(new Room("8", "3"));
        this.rooms.add(new Room("9", "3"));
        this.rooms.add(new Room("10", "3"));
        // SET PRICES
        setSingleRoomPrice("NULL");
        setDoubleRoomPrice("NULL");
        setDeluxeRoomPrice("NULL");
    }
    public void initialize(){}
}
