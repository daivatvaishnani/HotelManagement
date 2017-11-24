package com.example.shivam.HotelManagement.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.DataCollections.*;
import com.example.shivam.HotelManagement.DateDialog;
import com.example.shivam.HotelManagement.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.microedition.khronos.egl.EGLDisplay;

public class GuestActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText usernavname,usernavid;
    Button checkavailable,bookroom;
    EditText singleno,doubleno,deluxeno;
    EditText checkin,checkout;
    CheckBox checksingle,checkdouble,checkdeluxe;
    EditText noofguest,noofrooms;
    Button laundry,food,house;
    Spinner roomtype;
    EditText roomno,amount;
    TextView total;
    int roomstat = 0;
    ListView listview;
    private ProgressDialog progressdialog;
    static int indate,inmonth,inyear;
    static int outdate,outmonth,outyear;
    final User user = MainActivity.db.getActiveSession().getActiveUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestactivity);
       // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  usernavname = (EditText) findViewById(R.id.username);
        usernavid = (EditText) findViewById(R.id.usernavid);

        usernavname.setText(user.getUserName());
        usernavid.setText(user.getEmailId());*/
        amount = (EditText)findViewById(R.id.bill);
        total = (TextView) findViewById(R.id.totalbill);

        roomtype = (Spinner) findViewById(R.id.roomtype);
        roomno = (EditText) findViewById(R.id.roomno);

        checkin = (EditText) findViewById(R.id.checkin);
        checkout = (EditText) findViewById(R.id.checkout);

        noofguest = (EditText) findViewById(R.id.usernoguest);

        singleno = (EditText) findViewById(R.id.singleno);
        doubleno = (EditText) findViewById(R.id.doubleno);
        deluxeno = (EditText) findViewById(R.id.deluxeno);

        setgone(singleno,doubleno,deluxeno);
        amount.setVisibility(View.INVISIBLE);
        total.setVisibility(View.INVISIBLE);

        checksingle = (CheckBox) findViewById(R.id.usersingleroom);
        checkdouble = (CheckBox) findViewById(R.id.userdoubleroom);
        checkdeluxe = (CheckBox) findViewById(R.id.userdeluxeroom);

        checkavailable = (Button) findViewById(R.id.available);
        bookroom = (Button) findViewById(R.id.bookroom);

        laundry = (Button) findViewById(R.id.laundrybutton);
        food = (Button) findViewById(R.id.foodbutton);
        house = (Button) findViewById(R.id.housebutton);

        listview  = (ListView)findViewById(R.id.bill_list);

        List<String> categories = new ArrayList<String>();
        categories.add("Single");
        categories.add("Double");
        categories.add("Deluxe");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        roomtype.setAdapter(dataAdapter);

        laundry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String room_type = roomtype.getSelectedItem().toString();
                String room_no = roomno.getText().toString();
                Intent myIntent = new Intent(GuestActivity.this,
                        LaundryActivity.class);
                myIntent.putExtra("roomtype",room_type);
                myIntent.putExtra("roomno",room_no);
                startActivity(myIntent);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String room_type = roomtype.getSelectedItem().toString();
                String room_no = roomno.getText().toString();
                Intent myIntent = new Intent(GuestActivity.this,
                        FoodActivity.class);
                myIntent.putExtra("roomtype",room_type);
                myIntent.putExtra("roomno",room_no);
                startActivity(myIntent);
            }
        });
        setgone(laundry,food,house,roomtype,roomno);
        bookroom.setVisibility(View.INVISIBLE);

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
                    DateOperator dop = new DateOperator();
                    Date checkInDate = new Date();
                    Date checkOutDate = new Date();
                    try {
                        checkInDate = dop.stringToDate(in, '-');
                        checkOutDate = dop.stringToDate(out, '-');
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(nosingle.length() < 1) {nosingle = "0";}
                    if(nodouble.length() < 1) {nodouble = "0";}
                    if(nodeluxe.length() < 1) {nodeluxe = "0";}
                    roomstat = MainActivity.db.checkAvailablity(checkInDate, checkOutDate, nosingle, nodouble, nodeluxe);

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
                            //roomstat = 0;
                        }
                    }).start();

                   final String finalNosingle = nosingle;
                   new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            roomstat = 0;
                            if(roomstat == 1) {
                                Toast.makeText(GuestActivity.this, "SingleRooms are  not Available",Toast.LENGTH_SHORT).show();
                            }
                            else if(roomstat == 2) {
                                Toast.makeText(GuestActivity.this, "DoubleRooms are not Available",Toast.LENGTH_SHORT).show();
                            }
                            else if(roomstat == 3) {
                                Toast.makeText(GuestActivity.this, "DeluxeRooms are not Available",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(GuestActivity.this, "Rooms are Available",Toast.LENGTH_SHORT).show();
                                //int noSingle = Integer.parseInt(nosingle);
                                String guest = noofguest.getText().toString().trim();
                                String in = checkin.getText().toString();
                                String out = checkout.getText().toString();
                                int total_days = roomdays(in,out);
                                String nosingle = singleno.getText().toString().trim();
                                String nodouble = doubleno.getText().toString().trim();
                                String nodeluxe = deluxeno.getText().toString().trim();
                                if(nosingle.length() < 1) {nosingle = "0";}
                                if(nodouble.length() < 1) {nodouble = "0";}
                                if(nodeluxe.length() < 1) {nodeluxe = "0";}
                                int noSingle = Integer.parseInt(nosingle);
                                int noDouble = Integer.parseInt(nodouble);
                                int noDeluxe = Integer.parseInt(nodeluxe);
                                int singleRoomPrice = Integer.parseInt(MainActivity.db.getSingleRoomPrice());
                                int doubleRoomPrice = Integer.parseInt(MainActivity.db.getDoubleRoomPrice());
                                int deluxeRoomPrice = Integer.parseInt(MainActivity.db.getDeluxeRoomPrice());

                                int bill = (noSingle*singleRoomPrice + noDouble*doubleRoomPrice + noDeluxe*deluxeRoomPrice) * total_days;
                                System.out.println(bill);
                                String roombill = "$" + Integer.toString(bill);

                                bookroom.setVisibility(View.VISIBLE);
                                amount.setVisibility(View.VISIBLE);
                                total.setVisibility(View.VISIBLE);
                                amount.setText(roombill);
                            }
                        }
                    }, 3000);
                }
            }
        });

        bookroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateOperator dop = new DateOperator();
                String noOfGuest = noofguest.getText().toString();
                Date checkInDate = new Date();
                Date checkOutDate = new Date();
                try {
                    checkInDate = dop.stringToDate(checkin.getText().toString(), '-');
                    checkOutDate = dop.stringToDate(checkout.getText().toString(), '-');
                } catch (ParseException e) {
                    Toast.makeText(GuestActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                String noSingle = singleno.getText().toString();
                String noDouble = doubleno.getText().toString();
                String noDeluxe = deluxeno.getText().toString();

                if(noSingle.length() < 1) {noSingle = "0";}
                if(noDouble.length() < 1) {noDouble = "0";}
                if(noDeluxe.length() < 1) {noDeluxe = "0";}

                Toast.makeText(GuestActivity.this, user.getUserName(), Toast.LENGTH_SHORT);

                MainActivity.db.doBooking(user.getUserName(), checkInDate, checkOutDate, noSingle, noDouble, noDeluxe);

                Booking b = MainActivity.db.getActiveSession().getActiveUser().getLastBooking();
                String info = "BookingID : " + b.getBookingID() + "\n";
                ArrayList<Room> rooms = b.getRooms();
                for(Room r : rooms) {
                    info +=  "RoomID : " + r.getRoomID() + " RoomType : " + r.getRoomType() + "\n";
                }
                Toast.makeText(GuestActivity.this, info, Toast.LENGTH_LONG).show();
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
                String[] TO = {"manager@gmail.com"};
                String CC;
                CC = user.getEmailId();
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "FeedBack");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    //finish();

                } catch (android.content.ActivityNotFoundException ex) {}
               // emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*usernavname = (TextView)findViewById(R.id.usernavname);
        usernavid = (TextView)findViewById(R.id.usernavid);*/
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(GuestActivity.this);
        builder.setMessage("Are you sure you want to Log Out?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.db.getActiveSession().clearSession();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
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
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_roomservice) {
            setgone(singleno,doubleno,deluxeno,checkin,checkout,noofguest
                    ,checksingle,checkdouble,checkdeluxe,checkavailable,bookroom,amount,total);
            setvisible(laundry,food,house,roomtype,roomno);
            listview.setVisibility(View.GONE);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(GuestActivity.this, ChangeDetailsActivity.class));

        }else if (id == R.id.nav_feedback) {
            setgone(singleno,doubleno,deluxeno,checkin,checkout,noofguest
                    ,checksingle,checkdouble,checkdeluxe,checkavailable,bookroom,amount,total);
            setgone(laundry,food,house,roomtype,roomno);
            setgone(singleno,doubleno,deluxeno);
        }
        else if(id == R.id.nav_bookroom){
            setvisible(singleno,doubleno,deluxeno,checkin,checkout,noofguest
                    ,checksingle,checkdouble,checkdeluxe,checkavailable,bookroom,amount,total);
            listview.setVisibility(View.GONE);
            setgone(laundry,food,house,roomtype,roomno);
            setgone(singleno,doubleno,deluxeno);

        }
        else if(id == R.id.nav_checkout){
            try {
                setgone(singleno, doubleno, deluxeno, checkin, checkout, noofguest
                        , checksingle, checkdouble, checkdeluxe, checkavailable, bookroom, amount, total);
                setgone(laundry, food, house, roomtype, roomno);
                setgone(singleno, doubleno, deluxeno);
                Bill guestBill = MainActivity.db.checkOutUser(user.getUserName());
                String billAmount = guestBill.getBillAmount();
                ArrayList<String> printList = new ArrayList<>();
                String cnt = "1";
                for (Service s : guestBill.getServices()) {
                    String ad = "";
                    ad += "Service : " + cnt + "\n";
                    ad += "RoomID : " + s.getRoomID() + ", RoomType : " + printt(s.getRoomType()) + "\n";
                    ad += "ServiceType : " + s.getServiceType() + "\n";
                    for (Item i : s.getItems()) {
                        ad += "\t" + "ItemName : " + i.getItemName() + ", ItemPrice : " + i.getItemPrice() + ", ItemQuantity : " + i.getItemQuantity() + "\n";
                    }
                    ad += "Service Amount : " + s.getServiceAmount() + "\n\n";
                    printList.add(ad);
                }
                String ad = "";
                ad += "Total Bill Amount : " + billAmount + "\n\n";
                printList.add(ad);
                listview.setVisibility(View.VISIBLE);
                ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, printList) {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        TextView textView = (TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                        textView.setTextColor(Color.WHITE);

                        return view;
                    }
                };

                listview.setAdapter(listViewAdapter);
            }catch (Exception e){
                Toast.makeText(GuestActivity.this, "No Booking found", Toast.LENGTH_SHORT).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setgone(EditText single, EditText doub, EditText deluxe, EditText checkin, EditText checkout,
                        EditText noguest, CheckBox checksingle, CheckBox checkdouble, CheckBox checkdeluxe
            , Button checkav, Button book, EditText bill,TextView total){
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
        bill.setVisibility(View.GONE);
        total.setVisibility(View.GONE);
    }

    public void setvisible(EditText single,EditText doub,EditText deluxe,EditText checkin,EditText checkout,
                        EditText noguest,CheckBox checksingle,CheckBox checkdouble,CheckBox checkdeluxe
            ,Button checkav,Button book,EditText bill,TextView total){
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
        bill.setVisibility(View.INVISIBLE);
        total.setVisibility(View.INVISIBLE);
    }
    public void setgone(EditText single,EditText doub,EditText deluxe){
        single.setVisibility(View.INVISIBLE);
        doub.setVisibility(View.INVISIBLE);
        deluxe.setVisibility(View.INVISIBLE);
    }

    public void setgone(Button laundry, Button food, Button house, Spinner spin,EditText roomno){
        laundry.setVisibility(View.GONE);
        food.setVisibility(View.GONE);
        house.setVisibility(View.GONE);
        spin.setVisibility(View.GONE);
        roomno.setVisibility(View.GONE);
    }

    public void setvisible(Button laundry, Button food, Button house, Spinner spin,EditText roomno){
        laundry.setVisibility(View.VISIBLE);
        food.setVisibility(View.VISIBLE);
        house.setVisibility(View.VISIBLE);
        spin.setVisibility(View.VISIBLE);
        roomno.setVisibility(View.VISIBLE);
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
    public int roomdays(String in,String out){
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
        int total_days;
        if(outmonth>inmonth){
            int month_diff = outmonth - inmonth;
            int day_diff = outdate - indate;
            total_days = (day_diff) + (month_diff * 30);
        }
        else {
            int day_diff = outdate - indate;
            total_days = day_diff;
        }
        return total_days;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds groupItem to the action bar if it is present.
        getMenuInflater().inflate(R.menu.
                menu_logoutguest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logoutt) {
            MainActivity.db.getActiveSession().clearSession();
            startActivity(new Intent(GuestActivity.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String  printt(String t) {
        if(t == "1") {
            return "SingleRoom";
        }
        else if(t == "2") {
            return "DoubleRoom";
        }
        else {
            return "DeluxeRoom";
        }
    }

}
