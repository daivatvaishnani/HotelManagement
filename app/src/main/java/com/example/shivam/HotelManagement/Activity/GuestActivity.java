package com.example.shivam.HotelManagement.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.DataCollections.*;
import com.example.shivam.HotelManagement.DateDialog;
import com.example.shivam.HotelManagement.R;

public class GuestActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView usernavname,usernavid;
    Button checkavailable;
    EditText singleno,doubleno,deluxeno;
    EditText checkin,checkout;
    CheckBox checksingle,checkdouble,checkdeluxe;
    EditText noofguest,noofrooms;
    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestactivity);
       // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Bundle extras = getIntent().getExtras();
        User user = extras.getParcelable("user");*/

       /* usernavname = (TextView)findViewById(R.id.usernavname);
        usernavid = (TextView)findViewById(R.id.usernavid);*/

        User user = MainActivity.db.getActiveSession().getActiveUser();

        checkin = (EditText) findViewById(R.id.checkin);
        checkout = (EditText) findViewById(R.id.checkout);

        noofguest = (EditText) findViewById(R.id.usernoguest);

        singleno = (EditText) findViewById(R.id.singleno);
        doubleno = (EditText) findViewById(R.id.doubleno);
        deluxeno = (EditText) findViewById(R.id.deluxeno);

        setgone(singleno,doubleno,deluxeno);

        checksingle = (CheckBox) findViewById(R.id.usersingleroom);
        checkdouble = (CheckBox) findViewById(R.id.userdoubleroom);
        checkdeluxe = (CheckBox) findViewById(R.id.userdeluxeroom);

        checkavailable = (Button) findViewById(R.id.available);

        checkin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DateDialog dialog = new DateDialog(v);
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft,"Datepicker");

                }
            }
        });

        checkout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DateDialog dialog = new DateDialog(v);
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft,"Datepicker");

                }
            }
        });

       /* if(checksingle.isChecked()){
            singleno.setVisibility(View.VISIBLE);
        }
        if(checkdouble.isChecked()){
            doubleno.setVisibility(View.VISIBLE);
        }
        if(checkdeluxe.isChecked()){
            deluxeno.setVisibility(View.VISIBLE);
        }*/

        checksingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checksingle.isChecked()){
                    singleno.setVisibility(View.VISIBLE);
                }
            }
        });
        checkdouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkdouble.isChecked()){
                    doubleno.setVisibility(View.VISIBLE);
                }
            }
        });
        checkdeluxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkdeluxe.isChecked()){
                    deluxeno.setVisibility(View.VISIBLE);
                }
            }
        });



        checkavailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String guest = noofguest.getText().toString().trim();

                String nosingle = singleno.getText().toString().trim();
                String nodouble = doubleno.getText().toString().trim();
                String nodeluxe = deluxeno.getText().toString().trim();

                try{
                progressdialog.setMessage("Checking Availability");
                progressdialog.show();
                Thread.sleep(2000);
                progressdialog.dismiss();

            }catch(Exception e){}

                Toast.makeText(GuestActivity.this, "Room Are Available", Toast.LENGTH_SHORT).show();

                try{
                    progressdialog.setMessage("Proceeding to Payment Gateway");
                    progressdialog.show();
                    Thread.sleep(3000);
                    progressdialog.dismiss();

                }catch(Exception e){}

                startActivity(new Intent(GuestActivity.this, PaymentActivity.class));
                //query for availability
               /* boolean avialable = true;
                if(avialable){
                    //give him the required rooms
                }
                else {
                    progressdialog.setMessage("rooms not available");
                    progressdialog.dismiss();
                }*/
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guestactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setgone(EditText single,EditText doub,EditText deluxe){
        single.setVisibility(View.GONE);
        doub.setVisibility(View.GONE);
        deluxe.setVisibility(View.GONE);
    }
}
