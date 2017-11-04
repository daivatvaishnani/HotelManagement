package com.example.shivam.HotelManagement.DataCollections;

/**
 * Created by shivam on 1/11/17.
 */

public class User {
    String emailId;
    String pwd;
    String userAccessLevel;
    String shift;
    String userName;
    String roomID;
    Bill bill = new Bill();
    String phoneNo;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(String e, String p, String u, String uN, String pn)
    {
        this.emailId = e;
        this.pwd = p;
        this.userAccessLevel = u;
        this.userName = uN;
        this.phoneNo = pn;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserAccessLevel() {
        return userAccessLevel;
    }

    public void setUserAccessLevel(String userAccessLevel) {
        this.userAccessLevel = userAccessLevel;
    }
}
