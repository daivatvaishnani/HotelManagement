package com.example.shivam.HotelManagement.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shivam.HotelManagement.R;

public class LaundryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if( bundle.getString("some") != null){
                Toast.makeText(getApplicationContext(),"data:" + bundle.getString("some") , Toast.LENGTH_SHORT).show();
            }
        }
    }
}
