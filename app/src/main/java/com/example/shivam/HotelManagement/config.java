package com.example.shivam.HotelManagement;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by shivam on 11/10/17.
 */

public class config extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
