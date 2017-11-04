package com.example.shivam.HotelManagement.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.shivam.HotelManagement.Fragments.SimpleFragmentPagerAdapter;
import com.example.shivam.HotelManagement.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class ManagerActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);



//        firebaseAuth = FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser() == null)
//        {
//            finish();
//            startActivity(new Intent(getApplicationContext(),ManagerActivity.class));
//        }
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        SimpleFragmentPagerAdapter simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapter(this,getSupportFragmentManager());

        viewPager.setAdapter(simpleFragmentPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_layouts);
        tabLayout.setupWithViewPager(viewPager);

//        logOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(ManagerActivity.this, ManagerActivity.class));
//            }
//        });

    }
}
