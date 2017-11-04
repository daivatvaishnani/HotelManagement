package com.example.shivam.HotelManagement.Activity;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by shivam on 30/10/17.
 */

public class hotelmanagemnet extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
