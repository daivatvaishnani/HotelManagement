package com.example.shivam.HotelManagement.DataCollections;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shivam on 1/11/17.
 */

public class User {
    private String emailId;
    private String pwd;
    private String userAccessLevel;
    private String shift;
    private String userName;
    private String phoneNo;
    private String cardNo;
    private ArrayList<Booking> bookings = new ArrayList<>();
    private Date checkInDate;
    private Date checkoutDate;

    public boolean hasABooking() {
        if(bookings.isEmpty()) {
            return false;
        }
        return true;
    }

    public Booking getLastBooking() {
        if(this.hasABooking()) {
            return bookings.get(bookings.size() - 1);
        }
        else {
            return null;
        }
    }

    public boolean isEligibleForServices() {
        if(this.hasABooking()) {
            Date currDate = new Date();
            if(this.checkInDate.after(currDate)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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
