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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.DataCollections.User;
import com.example.shivam.HotelManagement.DateDialog;
import com.example.shivam.HotelManagement.R;

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
    Button bookroom;
    int roomstat = 0;
    private ProgressDialog progressdialog;
    static int indate,inmonth,inyear;
    static int outdate,outmonth,outyear;

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
        bookroom = (Button) findViewById(R.id.bookroom);

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
                String in = checkin.getText().toString();
                String out = checkout.getText().toString();
                String nosingle = singleno.getText().toString().trim();
                String nodouble = doubleno.getText().toString().trim();
                String nodeluxe = deluxeno.getText().toString().trim();


                Boolean datestat = checkdate(in,out);
                if(!datestat){
                    Toast.makeText(FDSActivity.this, "enter dates correctly", Toast.LENGTH_SHORT).show();
                }
                else if(in.equals("")){
                    Toast.makeText(FDSActivity.this, "enter check-in date", Toast.LENGTH_SHORT).show();
                }
                else if(out.equals("")){
                    Toast.makeText(FDSActivity.this, "enter check-out date", Toast.LENGTH_SHORT).show();
                }
                else if(guest.equals("")){
                    Toast.makeText(FDSActivity.this, "enter number of guests", Toast.LENGTH_SHORT).show();
                }
                else if(nosingle.equals("") && nodouble.equals("") && nodeluxe.equals("") ){
                    Toast.makeText(FDSActivity.this, "enter number of rooms", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(FDSActivity.this, "Rooms Are Available", Toast.LENGTH_SHORT).show();
                                bookroom.setVisibility(View.VISIBLE);
                            } else
                                Toast.makeText(FDSActivity.this, "Sorry!!..Rooms Are not Available", Toast.LENGTH_SHORT).show();
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
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(FDSActivity.this);
        builder.setMessage("Are you sure you want to Log Out?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
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

       if(outyear>inyear) flag = 1;
       else if(outyear==inyear){
           if(outmonth>inmonth) flag=1;
           else if(outmonth==inmonth){
               if(outdate>=indate) flag=1;
           }
       }
        System.out.println(flag);
        if(flag == 1) return true;
        else
            return false;
    }
}
