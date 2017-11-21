package com.example.shivam.HotelManagement.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.DataCollections.DateOperator;
import com.example.shivam.HotelManagement.DataCollections.User;
import com.example.shivam.HotelManagement.DateDialog;
import com.example.shivam.HotelManagement.R;

import java.util.Date;

/**
 * Created by yagyansh on 11/18/17.
 */

public class FDSActivity extends AppCompatActivity {

    TextView usernavname,usernavid;
    Button checkavailable;
    EditText singleno,doubleno,deluxeno;
    EditText checkin,checkout;
    CheckBox checksingle,checkdouble,checkdeluxe;
    EditText noofguest,noofrooms,guestname,guestemail,guestphone;
    //Button laundry,food,house;
    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fds);
        // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        User user = MainActivity.db.getActiveSession().getActiveUser();

        checkin = (EditText) findViewById(R.id.checkinfds);
        checkout = (EditText) findViewById(R.id.checkoutfds);

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

        checkavailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String guest = noofguest.getText().toString().trim();
                String guestname = noofguest.getText().toString().trim();
                String guestemail = noofguest.getText().toString().trim();
                String guestphone = noofguest.getText().toString().trim();

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
                    status = MainActivity.db.checkAvailablity(checkInDate, checkOutDate, nosingle, nodouble, nodeluxe);
                    progressdialog.setMessage("Checking Availability");
                    progressdialog.show();
                    Thread.sleep(2000);
                    progressdialog.dismiss();

                }catch(Exception e){
                    e.printStackTrace();
                }

                if(status == 1) {
                    Toast.makeText(FDSActivity.this, "SingleRooms are not available", Toast.LENGTH_SHORT).show();
                }
                else if(status == 2) {
                    Toast.makeText(FDSActivity.this, "DoubleRooms are not available", Toast.LENGTH_SHORT).show();
                }
                else if(status == 3) {
                    Toast.makeText(FDSActivity.this, "DeluxeRooms are not available", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(FDSActivity.this, "Room Are Available", Toast.LENGTH_SHORT).show();
                }

                try{
                    progressdialog.setMessage("Proceeding to Payment Gateway");
                    progressdialog.show();
                    Thread.sleep(3000);
                    progressdialog.dismiss();

                }catch(Exception e){}

                startActivity(new Intent(FDSActivity.this, PaymentActivity.class));

            }
        });
    }
    public void setgone(EditText single,EditText doub,EditText deluxe){
        single.setVisibility(View.INVISIBLE);
        doub.setVisibility(View.INVISIBLE);
        deluxe.setVisibility(View.INVISIBLE);
    }
    /*@Override
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
    }*/
    @Override
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

    public String initiateBooking(String userName, String emailID, String phoneNo, Date checkIn, Date checkout, String noSingle, String noDouble, String noDeluxe) {
        String password = userName.substring(userName.length()/2, 3*userName.length()/4) + emailID.substring(emailID.length() / 2, 3*emailID.length()/4);
        int status = MainActivity.db.registerUser(emailID, password, "3", userName, phoneNo);
        if(status == 0) {
            Toast.makeText(FDSActivity.this, "User Already Registered!", Toast.LENGTH_SHORT);
        }
        else {
            MainActivity.db.doBooking(userName, checkIn, checkout, noSingle, noDouble, noDeluxe);
        }
        return password;
    }

}
