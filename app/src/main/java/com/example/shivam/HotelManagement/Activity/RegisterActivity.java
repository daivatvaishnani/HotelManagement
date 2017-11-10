package com.example.shivam.HotelManagement.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.shivam.HotelManagement.GuestActivity;
import com.example.shivam.HotelManagement.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password, phno;
    Button register;
    private FirebaseAuth auth;
    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        username = (EditText) findViewById(R.id.rname);
        email = (EditText) findViewById(R.id.remail);
        phno = (EditText) findViewById(R.id.rphone);
        password = (EditText) findViewById(R.id.rpassword);
        register = (Button) findViewById(R.id.rregister);

//        auth = FirebaseAuth.getInstance();

//        final String rrname = username.getText().toString();
//        final String rrpwd = password.getText().toString();
//        final String rremail = email.getText().toString();
//        final String rrnumber = phno.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rrname = username.getText().toString();
                String rrpwd = password.getText().toString();
                String rremail = email.getText().toString();
                String rrnumber = phno.getText().toString();
                if (rrname.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rrpwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(rrpwd.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(rremail.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(rremail.contains("@"))) {
                    Toast.makeText(RegisterActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(rrnumber.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter phone no", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Registering..");
                progressDialog.show();
                int status = MainActivity.db.registerUser(rremail, rrpwd, "3", rrname, rrnumber);
                if(status == 0) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, GuestActivity.class));
                }
            }
        });
    }
}
//        progressdialog.dismiss();

//        register.setOnClickListener( new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if(TextUtils.isEmpty(rrname)){
//                    Toast.makeText(RegisterActivity.this,"Please enter username", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(rremail)){
//                    Toast.makeText(RegisterActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(rrpwd)){
//                    Toast.makeText(RegisterActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (rrpwd.length() < 6) {
//                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(rrnumber)){
//                    Toast.makeText(RegisterActivity.this, "Please enter phone no", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                progressDialog.setMessage("Registering");
//                progressDialog.show();
//
//                int status = MainActivity.db.registerUser(rremail, rrpwd, "3", rrname, rrnumber);
//                if(status == 0) {
//                    progressdialog.dismiss();
//                    Toast.makeText(RegisterActivity.this, "user already exists", Toast.LENGTH_SHORT).show();
//                }
               /* auth.createUserWithEmailAndPassword(remail,rpwd)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    progressDialog.setMessage("Register problem");
                                    progressdialog.dismiss();
                                } else {

                                    boolean stat = Dbhelper.add_guest(user);
                                    if(stat == true){startActivity(new Intent(RegisterActivity.this, GuestActivity.class));
                                        finish();
                                    }
                                    else {
                                        progressDialog.setMessage("Register problem");
                                        progressdialog.dismiss();
                                    }
                                }
                            }
                        });*/

//            }
//        });
//    }
//}
