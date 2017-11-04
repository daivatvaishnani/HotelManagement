package com.example.shivam.HotelManagement.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.DateDialog;
import com.example.shivam.HotelManagement.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
{


   // FirebaseAuth mAuth;

   // private DatabaseReference databaseReference;

   // EditText singleprice,doubleprice,deluxeprice;
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setprice);

        //databaseReference = FirebaseDatabase.getInstance().getReference();
        //mAuth = FirebaseAuth.getInstance();

       // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
       // Toast.makeText(DateActivity.this,"Information Saved...",Toast.LENGTH_SHORT).show();
        EditText singleprice = (EditText) findViewById(R.id.singleprice);
        EditText doubleprice = (EditText) findViewById(R.id.doubleprice);
        EditText deluxeprice = (EditText) findViewById(R.id.deluxeprice);
        Button SetPrice = (Button) findViewById(R.id.price);
        Button logOut = (Button) findViewById(R.id.logOut);

        final String singprice = singleprice.getText().toString();
        final String doubprice = doubleprice.getText().toString();
        final String delprice = deluxeprice.getText().toString();

        //run query to set prices

       /* SetPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DateActivity.this,"Information Saved...",Toast.LENGTH_SHORT).show();
                setPriceRoom(singprice,doubprice);
            }
        });*/

       /* logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(DateActivity.this, MainActivity.class));
            }
        });*/




   /* public void setPriceRoom(String singleprice,String doubleprice)
    {
        RoomInformation ri = new RoomInformation(singleprice,doubleprice
        );

        FirebaseUser user = mAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(ri);

        Toast.makeText(DateActivity.this,"Information Saved...",Toast.LENGTH_SHORT).show();

    }*/

    /*public void onstart(){
        super.onStart();
        EditText startdate = (EditText) findViewById(R.id.startdate);
        EditText enddate = (EditText) findViewById(R.id.enddate);

        startdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DateDialog dialog = new DateDialog(v);
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft,"Datepicker");

                }
            }
        });


        enddate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DateDialog dialog = new DateDialog(v);
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft,"Datepicker");

                }
            }
        });*/



    Button start,end;
    Calendar mCurrentDate1,mCurrentDate2;
    int day1,month1,year1;
    int day2,month2,year2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setprice);

        start = (Button) findViewById(R.id.startdate);
        end =  (Button) findViewById(R.id.enddate);

        mCurrentDate1 = Calendar.getInstance();
        mCurrentDate2 = Calendar.getInstance();

        day1 = mCurrentDate1.get(Calendar.DAY_OF_MONTH);
        month1 = mCurrentDate1.get(Calendar.MONTH);
        year1 = mCurrentDate1.get(Calendar.YEAR);

        day2 = mCurrentDate2.get(Calendar.DAY_OF_MONTH);
        month2 = mCurrentDate2.get(Calendar.MONTH);
        year2 = mCurrentDate2.get(Calendar.YEAR);

        month1 = month1 + 1;
        month2 = month2 + 1;

        start.setText(day1+"/"+month1+"/"+year1);
        end.setText(day2+"/"+month2+"/"+year2);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog1 = new DatePickerDialog(DateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        start.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },year1, month1, day1);
                datePickerDialog1.show();
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog2 = new DatePickerDialog(DateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month + 1;
                        end.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },year2, month2, day2);
                datePickerDialog2.show();
            }
        });



    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
