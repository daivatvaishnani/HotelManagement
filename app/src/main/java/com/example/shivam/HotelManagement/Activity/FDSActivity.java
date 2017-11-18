package com.example.shivam.HotelManagement.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
    EditText noofguest,noofrooms;
    Button laundry,food,house;
    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fds);
        // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*Bundle extras = getIntent().getExtras();
        User user = extras.getParcelable("user");*/

       /* usernavname = (TextView)findViewById(R.id.usernavname);
        usernavid = (TextView)findViewById(R.id.usernavid);*/

        User user = MainActivity.db.getActiveSession().getActiveUser();

        checkin = (EditText) findViewById(R.id.checkinfds);
        checkout = (EditText) findViewById(R.id.checkoutfds);

        noofguest = (EditText) findViewById(R.id.usernoguestfds);

        singleno = (EditText) findViewById(R.id.singlenofds);
        doubleno = (EditText) findViewById(R.id.doublenofds);
        deluxeno = (EditText) findViewById(R.id.deluxenofds);


        checksingle = (CheckBox) findViewById(R.id.usersingleroomfds);
        checkdouble = (CheckBox) findViewById(R.id.userdoubleroomfds);
        checkdeluxe = (CheckBox) findViewById(R.id.userdeluxeroomfds);

        checkavailable = (Button) findViewById(R.id.availablefds);

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

                Toast.makeText(FDSActivity.this, "Room Are Available", Toast.LENGTH_SHORT).show();

                try{
                    progressdialog.setMessage("Proceeding to Payment Gateway");
                    progressdialog.show();
                    Thread.sleep(3000);
                    progressdialog.dismiss();

                }catch(Exception e){}

                startActivity(new Intent(FDSActivity.this, PaymentActivity.class));
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


    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guestactivity, menu);
        return true;
    }
*/
    /*@Override
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
    }*/

}
