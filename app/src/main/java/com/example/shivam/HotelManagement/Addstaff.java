package com.example.shivam.HotelManagement;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;

import com.example.shivam.HotelManagement.Activity.MainActivity;
import com.example.shivam.HotelManagement.Activity.ManagerActivity;
import com.example.shivam.HotelManagement.Activity.RegisterActivity;
import com.example.shivam.HotelManagement.Activity.SupervisorActivity;
import com.example.shivam.HotelManagement.DataCollections.*;

import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//import com.example.shivam.HotelManagement.Database.Dbhelper;

import com.example.shivam.HotelManagement.Fragments.MaintainDetailsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Addstaff extends Activity implements AdapterView.OnItemSelectedListener {

    EditText username,email,password,phno;
    Button add;
    String stafftype;
    private FirebaseAuth auth;
    private ProgressDialog progressdialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstaff);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        
        username = (EditText) findViewById(R.id.sname);
        email = (EditText) findViewById(R.id.semail);
        phno = (EditText) findViewById(R.id.sphone);
        password = (EditText) findViewById(R.id.spassword);
        add = (Button) findViewById(R.id.sadd);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        final String access =  MainActivity.db.getActiveSession().getActiveUser().getUserAccessLevel();

        if(access.equals("1")){
            spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

            List<String> categories = new ArrayList<String>();
            categories.add("FDS");
            categories.add("Supervisor");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(dataAdapter);
        }
        else{
            spinner.setVisibility(View.GONE);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int status;
                final String sname = username.getText().toString();
                final String spwd = password.getText().toString();
                final String semail = email.getText().toString();
                final String snumber = phno.getText().toString();
                String sual = "5";
                if(access.equals("2")) {sual = "5";}
                else {
                    sual = spinner.getSelectedItem().toString();
                    if(sual.equals("FDS")) {sual = "4";}
                    if(sual.equals("Supervisor")) {sual = "2";}
                }
                if(TextUtils.isEmpty(sname)){
                    Toast.makeText(Addstaff.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(semail)){
                    Toast.makeText(Addstaff.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!semail.contains("@")) {
                    Toast.makeText(Addstaff.this, "Invalid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(spwd)){
                    Toast.makeText(Addstaff.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (spwd.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(snumber)){
                    Toast.makeText(Addstaff.this, "Please enter phone no", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    int stat = MainActivity.db.registerUser(semail, spwd, sual, sname, snumber);
                    if (stat == 0) {
                        Toast.makeText(Addstaff.this, "User already registred!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Addstaff.this, "staff has been added", Toast.LENGTH_SHORT).show();
                        String pwd = initiateBooking(sname,semail);
                        SmsManager smsManager = SmsManager.getDefault();
                        String sms = "Your password is :" +pwd;
                        try{
                            smsManager.sendTextMessage(snumber, null, sms, null, null);
                        }catch(Exception e){}
                    }
                }
                finish();
                if(access.equals("1")) {
                    startActivity(new Intent(Addstaff.this, ManagerActivity.class));
                }
                else {
                    startActivity(new Intent(Addstaff.this, SupervisorActivity.class));
                }
            }
        });


        

        final ProgressDialog progressDialog = new ProgressDialog(this);

        /*add.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                progressDialog.setMessage("Adding");
                progressDialog.show();

                auth.createUserWithEmailAndPassword(semail,spwd)
                        .addOnCompleteListener(Addstaff.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    progressDialog.setMessage("Register problem");
                                    progressdialog.dismiss();
                                } else {
                                    User user;
                                    if(stafftype == "FDS") {
                                         user = new User(semail, spwd, sname, snumber, "FDS");
                                        boolean stat = Dbhelper.add_guest(user);
                                        Toast.makeText(getApplicationContext(), "new staff added", Toast.LENGTH_SHORT).show();
                                        if(stat == true){
                                            startActivity(new Intent(Addstaff.this, employeedetails.class));
                                            finish();
                                        }
                                    }
                                    else if(stafftype == "Supervisor"){
                                         user = new User(semail, spwd, sname, snumber, "Supervisor");
                                        //boolean stat = Dbhelper.add_guest(user);
                                        if(stat == true){
                                            startActivity(new Intent(Addstaff.this, employeedetails.class));
                                            finish();
                                        }
                                    }
                                    else {
                                        progressDialog.setMessage("Adding problem");
                                        progressdialog.dismiss();
                                    }
                                }
                            }
                        });

            }
        });*/
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        stafftype = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public String initiateBooking(String userName, String emailID) {
        String password = userName.substring(userName.length()/2, 3*userName.length()/4) + emailID.substring(emailID.length() / 2, 3*emailID.length()/4);

        return password;
    }
    
}
