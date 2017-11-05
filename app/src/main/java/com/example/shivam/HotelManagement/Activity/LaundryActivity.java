package com.example.shivam.HotelManagement.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shivam.HotelManagement.DataCollections.Item;
import com.example.shivam.HotelManagement.R;

import java.util.ArrayList;

public class LaundryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);

        // To get all items pass no parameter
        ArrayList<Item> itemsList = MainActivity.db.getItems("Laundry");
        // To retrieve Food items list pass the parameter "Food"

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if( bundle.getString("some") != null){
                Toast.makeText(getApplicationContext(),"data:" + bundle.getString("some") , Toast.LENGTH_SHORT).show();
            }
        }
    }
}
