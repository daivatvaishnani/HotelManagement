package com.example.shivam.HotelManagement.Activity;

/**
 * Created by shivam on 31/10/17.
 */

public class Supervisor
{
    String emailId;
    String name;
    int shiftNum;

    public Supervisor(String email,String name,int s)
    {
        this.emailId = email;
        this.name = name;
        this.shiftNum = s;
    }
}
