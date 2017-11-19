package com.example.shivam.HotelManagement.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
    Button checkavailable,bookroom;
    EditText singleno,doubleno,deluxeno;
    EditText checkin,checkout;
    CheckBox checksingle,checkdouble,checkdeluxe;
    EditText noofguest,noofrooms;
    Button laundry,food,house;
    int roomstat = 0;
    private ProgressDialog progressdialog;
    static int indate,inmonth,inyear;
    static int outdate,outmonth,outyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestactivity);
       // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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
        bookroom = (Button) findViewById(R.id.bookroom);

        laundry = (Button) findViewById(R.id.laundrybutton);
        food = (Button) findViewById(R.id.foodbutton);
        house = (Button) findViewById(R.id.housebutton);

        laundry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(GuestActivity.this,
                        LaundryActivity.class);
                startActivity(myIntent);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(GuestActivity.this,
                        FoodActivity.class);
                startActivity(myIntent);
            }
        });
        setgone(laundry,food,house);
        bookroom.setVisibility(View.INVISIBLE);


        /*checkin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        });*/

       checkin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DateDialog dialog = new DateDialog(v);
               android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
               dialog.show(ft,"Datepicker");
           }
       });

       checkout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DateDialog dialog = new DateDialog(v);
               android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
               dialog.show(ft,"Datepicker");
           }
       });

        checksingle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(checksingle.isChecked()){
                    singleno.setVisibility(View.VISIBLE);
                }
                if(!checksingle.isChecked()){
                    singleno.setVisibility(View.INVISIBLE);
                }
            }
        });
        checkdouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkdouble.isChecked()){
                    doubleno.setVisibility(View.VISIBLE);
                }
                if(!checkdouble.isChecked()){
                    doubleno.setVisibility(View.INVISIBLE);
                }
            }
        });
        checkdeluxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkdeluxe.isChecked()){
                    deluxeno.setVisibility(View.VISIBLE);
                }
                if(!checkdeluxe.isChecked()){
                    deluxeno.setVisibility(View.INVISIBLE);
                }
            }
        });



        checkavailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String guest = noofguest.getText().toString().trim();
                String in = checkin.getText().toString();
                String out = checkout.getText().toString();
                String nosingle = singleno.getText().toString().trim();
                String nodouble = doubleno.getText().toString().trim();
                String nodeluxe = deluxeno.getText().toString().trim();


               Boolean datestat = checkdate(in,out);
               if(!datestat){
                   Toast.makeText(GuestActivity.this, "enter dates correctly", Toast.LENGTH_SHORT).show();
               }
                else if(in.equals("")){
                    Toast.makeText(GuestActivity.this, "enter check-in date", Toast.LENGTH_SHORT).show();
                }
                else if(out.equals("")){
                    Toast.makeText(GuestActivity.this, "enter check-out date", Toast.LENGTH_SHORT).show();
                }
                else if(guest.equals("")){
                    Toast.makeText(GuestActivity.this, "enter number of guests", Toast.LENGTH_SHORT).show();
                }
                else if(nosingle.equals("") && nodouble.equals("") && nodeluxe.equals("") ){
                   Toast.makeText(GuestActivity.this, "enter number of rooms", Toast.LENGTH_SHORT).show();
               }

                else {
                    progressdialog = new ProgressDialog(v.getContext());
                    progressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressdialog.setMessage("Checking Availability...");
                    progressdialog.show();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progressdialog.dismiss();
                            roomstat = 1;
                        }
                    }).start();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            roomstat = 1;
                            if (roomstat == 1) {
                                Toast.makeText(GuestActivity.this, "Rooms Are Available", Toast.LENGTH_SHORT).show();
                                bookroom.setVisibility(View.VISIBLE);
                            } else
                                Toast.makeText(GuestActivity.this, "Sorry!!..Rooms Are not Available", Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);
                }


            }
        });

        bookroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressdialog = new ProgressDialog(v.getContext());
                progressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressdialog.setMessage("Proceeding to Payment Portal");
                progressdialog.show();

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressdialog.dismiss();
                    }
                }).start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(GuestActivity.this, PaymentActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 4000);
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

        usernavname = (TextView)findViewById(R.id.usernavname);
        usernavid = (TextView)findViewById(R.id.usernavid);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_roomservice) {
            setgone(singleno,doubleno,deluxeno,checkin,checkout,noofguest
                    ,checksingle,checkdouble,checkdeluxe,checkavailable,bookroom);
            setvisible(laundry,food,house);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {

        }else if (id == R.id.nav_feedback) {
            setgone(singleno,doubleno,deluxeno,checkin,checkout,noofguest
                    ,checksingle,checkdouble,checkdeluxe,checkavailable,bookroom);
            setgone(laundry,food,house);
            setgone(singleno,doubleno,deluxeno);
        }
        else if(id == R.id.nav_bookroom){
            setvisible(singleno,doubleno,deluxeno,checkin,checkout,noofguest
                    ,checksingle,checkdouble,checkdeluxe,checkavailable,bookroom);

            setgone(laundry,food,house);
            setgone(singleno,doubleno,deluxeno);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setgone(EditText single,EditText doub,EditText deluxe,EditText checkin,EditText checkout,
                        EditText noguest,CheckBox checksingle,CheckBox checkdouble,CheckBox checkdeluxe
            ,Button checkav,Button book){
        single.setVisibility(View.GONE);
        doub.setVisibility(View.GONE);
        deluxe.setVisibility(View.GONE);
        checkin.setVisibility(View.GONE);
        checkout.setVisibility(View.GONE);
        noguest.setVisibility(View.GONE);
        checksingle.setVisibility(View.GONE);
        checkdouble.setVisibility(View.GONE);
        checkdeluxe.setVisibility(View.GONE);
        checkav.setVisibility(View.GONE);
        book.setVisibility(View.GONE);
    }

    public void setvisible(EditText single,EditText doub,EditText deluxe,EditText checkin,EditText checkout,
                        EditText noguest,CheckBox checksingle,CheckBox checkdouble,CheckBox checkdeluxe
            ,Button checkav,Button book){
        single.setVisibility(View.VISIBLE);
        doub.setVisibility(View.VISIBLE);
        deluxe.setVisibility(View.VISIBLE);
        checkin.setVisibility(View.VISIBLE);
        checkout.setVisibility(View.VISIBLE);
        noguest.setVisibility(View.VISIBLE);
        checksingle.setVisibility(View.VISIBLE);
        checkdouble.setVisibility(View.VISIBLE);
        checkdeluxe.setVisibility(View.VISIBLE);
        checkav.setVisibility(View.VISIBLE);
        book.setVisibility(View.INVISIBLE);
    }
    public void setgone(EditText single,EditText doub,EditText deluxe){
        single.setVisibility(View.INVISIBLE);
        doub.setVisibility(View.INVISIBLE);
        deluxe.setVisibility(View.INVISIBLE);
    }

    public void setgone(Button laundry, Button food, Button house){
        laundry.setVisibility(View.GONE);
        food.setVisibility(View.GONE);
        house.setVisibility(View.GONE);
    }

    public void setvisible(Button laundry, Button food, Button house){
        laundry.setVisibility(View.VISIBLE);
        food.setVisibility(View.VISIBLE);
        house.setVisibility(View.VISIBLE);
    }
    Boolean checkdate(String in, String out){
        int flag = 0;
        if(in.length()==10){
             inyear = Integer.parseInt(in.substring(6,10));
             indate = Integer.parseInt(in.substring(0,2));
             inmonth = Integer.parseInt(in.substring(3,5));
        }
        if(in.length()==9){
            indate = Integer.parseInt(in.substring(0,1));
            inmonth = Integer.parseInt(in.substring(2,4));
            inyear = Integer.parseInt(in.substring(5,9));
        }
        if(out.length()==10){
            outdate = Integer.parseInt(out.substring(0,2));
            outmonth = Integer.parseInt(out.substring(3,5));
            outyear = Integer.parseInt(out.substring(6,10));
        }
        if(out.length()==9){
            outdate = Integer.parseInt(out.substring(0,1));
            outmonth = Integer.parseInt(out.substring(2,4));
            outyear = Integer.parseInt(out.substring(5,9));
        }
        System.out.println(indate + " " + inmonth + " " + inyear);
        System.out.println(outdate + " " + outmonth + " " + outyear);

        if(outyear >= inyear){
            if(outmonth >= inmonth  ){
                if(outdate >= indate)
                    flag = 1;
            }
        }
        System.out.println(flag);
        if(flag == 1) return true;
        else
            return false;
    }
}
