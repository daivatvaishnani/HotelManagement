package com.example.shivam.HotelManagement.Activity;

/**
 * Created by shivam on 31/10/17.
 */

public class Guests
{
    String emailID;
    String name;
    String roomNo;
    int accBal = 0;

    public void updateBal(int newBal)
    {
        accBal = accBal + newBal;
    }

    public Guests(String e,String n,String r,int a)
    {
        this.emailID = e;
        this.name = n;
        this.roomNo = r;
        this.accBal = a;
    }

}
