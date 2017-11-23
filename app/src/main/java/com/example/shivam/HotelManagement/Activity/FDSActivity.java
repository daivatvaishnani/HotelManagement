package com.example.shivam.HotelManagement.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.DataCollections.Booking;
import com.example.shivam.HotelManagement.DataCollections.DateOperator;
import com.example.shivam.HotelManagement.DataCollections.Room;
import com.example.shivam.HotelManagement.DataCollections.User;
import com.example.shivam.HotelManagement.DateDialog;
import com.example.shivam.HotelManagement.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by yagyansh on 11/18/17.
 */

public class FDSActivity extends AppCompatActivity {

    TextView usernavname,usernavid;
    Button checkavailable,bookroom;
    EditText singleno,doubleno,deluxeno;
    EditText checkin,checkout;
    CheckBox checksingle,checkdouble,checkdeluxe;
    EditText noofguest,noofrooms,guestname,guestemail,guestphone;
    //Button laundry,food,house;
    int roomstat = 0;
    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fds);
        // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        final User user = MainActivity.db.getActiveSession().getActiveUser();

        checkin = (EditText) findViewById(R.id.checkinfds);
        checkout = (EditText) findViewById(R.id.checkoutfds);
/* progressdialog.setMessage("Checking Availability");
                    progressdialog.show();
                    Thread.sleep(2000);
                    progressdialog.dismiss();*/
        guestname = (EditText) findViewById(R.id.guestname);
        guestemail = (EditText) findViewById(R.id.guestemail);
        guestphone = (EditText) findViewById(R.id.guestphone);
        noofguest = (EditText) findViewById(R.id.usernoguestfds);

        singleno = (EditText) findViewById(R.id.singlenofds);
        doubleno = (EditText) findViewById(R.id.doublenofds);
        deluxeno = (EditText) findViewById(R.id.deluxenofds);

        setgone(singleno,doubleno,deluxeno);

        checksingle = (CheckBox) findViewById(R.id.usersingleroomfds);
        checkdouble = (CheckBox) findViewById(R.id.userdoubleroomfds);
        checkdeluxe = (CheckBox) findViewById(R.id.userdeluxeroomfds);

        checkavailable = (Button) findViewById(R.id.availablefds);
        bookroom = (Button) findViewById(R.id.bookroom);

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

        checksingle.setOnClickListener(new View.OnClickListener() {
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
        final String[] guest_name = new String[1];
        final String[] guest_email = new String[1];
        final String[] guest_phone = new String[1];
        final String[] guest = new String[1];

        checkavailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 guest[0] = noofguest.getText().toString().trim();
                guest_name[0] = guestname.getText().toString().trim();
                 guest_email[0] = guestemail.getText().toString().trim();
                guest_phone[0] = guestphone.getText().toString().trim();

                if(guest[0].equals("")){
                    Toast.makeText(FDSActivity.this, "Enter the no of guest",Toast.LENGTH_SHORT).show();
                }
                else if(guest_name[0].equals("")){
                    Toast.makeText(FDSActivity.this, "Enter the name",Toast.LENGTH_SHORT).show();
                }
                else if(guest_email[0].equals("")){
                    Toast.makeText(FDSActivity.this, "Enter the email",Toast.LENGTH_SHORT).show();
                }
                else if(guest_phone[0].equals("")){
                    Toast.makeText(FDSActivity.this, "Enter the phone no",Toast.LENGTH_SHORT).show();
                }

                String nosingle = singleno.getText().toString().trim();
                String nodouble = doubleno.getText().toString().trim();
                String nodeluxe = deluxeno.getText().toString().trim();
                String checkIn = checkin.getText().toString();
                String checkOut = checkout.getText().toString();

                Date checkInDate = new Date();
                Date checkOutDate = new Date();
                int status = -1;

                try{

                    DateOperator dop = new DateOperator();
                    checkInDate = dop.stringToDate(checkIn, '-');
                    checkOutDate = dop.stringToDate(checkOut, '-');
                    roomstat = MainActivity.db.checkAvailablity(checkInDate, checkOutDate, nosingle, nodouble, nodeluxe);

                }catch(Exception e){
                    e.printStackTrace();
                }
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
                        roomstat = 0;
                    }
                }).start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        roomstat = 0;
                        if(roomstat == 1) {
                            Toast.makeText(FDSActivity.this, "SingleRooms are  not Available",Toast.LENGTH_SHORT).show();
                        }
                        else if(roomstat == 2) {
                            Toast.makeText(FDSActivity.this, "DoubleRooms are not Available",Toast.LENGTH_SHORT).show();
                        }
                        else if(roomstat == 3) {
                            Toast.makeText(FDSActivity.this, "DeluxeRooms are not Available",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(FDSActivity.this, "Rooms are Available",Toast.LENGTH_SHORT).show();
                            bookroom.setVisibility(View.VISIBLE);
                        }
                    }
                }, 3000);
                //startActivity(new Intent(FDSActivity.this, PaymentActivity.class));

            }
        });

        bookroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = initiateBooking(guest_name[0], guest_email[0]);
               int stat =  MainActivity.db.registerUser(guest_email[0],pwd,"3",guest_name[0],guest_phone[0]);
                SmsManager smsManager = SmsManager.getDefault();
                String sms = "your password is :" +pwd;
                try{
                    smsManager.sendTextMessage(guest_phone[0], null, sms, null, null);
                }catch(Exception e){}
                DateOperator dop = new DateOperator();
                String noOfGuest = noofguest.getText().toString();
                Date checkInDate = new Date();
                Date checkOutDate = new Date();
                try {
                    checkInDate = dop.stringToDate(checkin.getText().toString(), '-');
                    checkOutDate = dop.stringToDate(checkout.getText().toString(), '-');
                } catch (ParseException e) {
                    Toast.makeText(FDSActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                String noSingle = singleno.getText().toString();
                String noDouble = doubleno.getText().toString();
                String noDeluxe = deluxeno.getText().toString();

                if(noSingle.length() < 1) {noSingle = "0";}
                if(noDouble.length() < 1) {noDouble = "0";}
                if(noDeluxe.length() < 1) {noDeluxe = "0";}

                Toast.makeText(FDSActivity.this,guest_name[0], Toast.LENGTH_SHORT);

                MainActivity.db.doBooking(guest_name[0], checkInDate, checkOutDate, noSingle, noDouble, noDeluxe);

                Booking b = MainActivity.db.getUser(guest_email[0]).getLastBooking();
                String info = "BookingID : " + b.getBookingID() + "\n";
                ArrayList<Room> rooms = b.getRooms();
                for(Room r : rooms) {
                    info +=  "RoomID : " + r.getRoomID() + " RoomType : " + r.getRoomType() + "\n";
                }
                Toast.makeText(FDSActivity.this, info, Toast.LENGTH_LONG).show();
//
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
                        Intent i = new Intent(FDSActivity.this, PaymentActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 4000);
            }
        });

    }
    public void setgone(EditText single,EditText doub,EditText deluxe){
        single.setVisibility(View.INVISIBLE);
        doub.setVisibility(View.INVISIBLE);
        deluxe.setVisibility(View.INVISIBLE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds groupItem to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            MainActivity.db.getActiveSession().clearSession();
            startActivity(new Intent(FDSActivity.this, MainActivity.class));
            return true;
        }

        if (id == R.id.action_settings) {
            startActivity(new Intent(FDSActivity.this, ChangeDetailsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        final AlertDialog.Builder builder = new AlertDialog.Builder(FDSActivity.this);
        builder.setMessage("Are you sure you want to Log Out?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.db.getActiveSession().clearSession();
                finish();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public String initiateBooking(String userName, String emailID) {
        String password = userName.substring(userName.length()/2, 3*userName.length()/4) + emailID.substring(emailID.length() / 2, 3*emailID.length()/4);
        /*int status = MainActivity.db.registerUser(emailID, password, "3", userName, phoneNo);
        if(status == 0) {
            Toast.makeText(FDSActivity.this, "User Already Registered!", Toast.LENGTH_SHORT);
        }
        else {
            MainActivity.db.doBooking(userName, checkIn, checkout, noSingle, noDouble, noDeluxe);
        }*/
        return password;
    }

}
