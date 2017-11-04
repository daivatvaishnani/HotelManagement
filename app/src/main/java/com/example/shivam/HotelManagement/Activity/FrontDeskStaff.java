package com.example.shivam.HotelManagement.Activity;

/**
 * Created by shivam on 31/10/17.
 */

public class FrontDeskStaff
{
    String name;
    String emailId;
    int shiftNum;

    public FrontDeskStaff(String email,String name,int s)
    {
        this.emailId = email;
        this.name = name;
        this.shiftNum = s;
    }
}
