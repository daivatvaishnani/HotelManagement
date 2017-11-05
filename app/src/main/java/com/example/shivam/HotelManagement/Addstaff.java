package com.example.shivam.HotelManagement;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.example.shivam.HotelManagement.DataCollections.*;

import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//import com.example.shivam.HotelManagement.Database.Dbhelper;

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

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        List<String> categories = new ArrayList<String>();
        categories.add("FDS");
        categories.add("Supervisor");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        final String sname = username.getText().toString();
        final String spwd = password.getText().toString();
        final String semail = email.getText().toString();
        final String snumber = phno.getText().toString();
        

        final ProgressDialog progressDialog = new ProgressDialog(this);

        /*add.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(sname)){
                    Toast.makeText(Addstaff.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(semail)){
                    Toast.makeText(Addstaff.this, "Please enter email", Toast.LENGTH_SHORT).show();
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
    
    
}
