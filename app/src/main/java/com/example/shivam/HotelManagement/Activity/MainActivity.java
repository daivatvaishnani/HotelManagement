package com.example.shivam.HotelManagement.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shivam.HotelManagement.DataCollections.*;
//import com.example.shivam.HotelManagement.Activity.GuestActivity;
import com.example.shivam.HotelManagement.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public static Database db = new Database();

    private Button login;
    private Button register;
    private EditText user;
    private EditText pwrd;
    private Firebase mRef;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(db.getActiveSession().isActive()) {
            final User ActiveUser = db.getActiveSession().getActiveUser();
            String ual = ActiveUser.getUserAccessLevel();
            if(ual.equals("1")) {
                //Manager
                startActivity(new Intent(MainActivity.this, ManagerActivity.class));
            }
            else if(ual.equals("2")) {
                //Supervisor
                startActivity(new Intent(MainActivity.this, SupervisorActivity.class));
            }
            else if(ual.equals("3")) {
                //Guest
                startActivity(new Intent(MainActivity.this, GuestActivity.class));
            }
            else if(ual.equals("4")) {
                //FDS
            }
        }
        try {
            db.initialize();
        }
        catch (Exception e) {
           Toast.makeText(MainActivity.this,e.toString(), Toast.LENGTH_LONG).show();

//            Log.d("x", e.toString());
        }
        // mRef = new Firebase("https://hotel-management-a47d3.firebaseio.com/");

        // mAuth = FirebaseAuth.getInstance();

        //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //   Firebase.setAndroidContext(getApplicationContext());
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        user = (EditText) findViewById(R.id.username);
        pwrd = (EditText) findViewById(R.id.password);


        progressDialog = new ProgressDialog(this);

      /*  mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                if (firebaseAuth.getCurrentUser() != null)
                {
                    startActivity(new Intent(MainActivity.this,ManagerActivity.class));
                }
            }
        };*/


        //System.out.println(email);


//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent(MainActivity.this,ManagerActivity.class);
//                startActivity(in);
//                    if(TextUtils.isEmpty(email)){
//                        Toast.makeText(MainActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                if(TextUtils.isEmpty(pwd)){
//                    Toast.makeText(MainActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                progressdialog.setMessage("Logging in");
//                progressdialog.show();
//                mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(!task.isSuccessful()){
//                            progressdialog.setMessage("sign in problem");
//                            progressdialog.dismiss();
//                            //Toast.makeText(MainActivity.this, "sign in problem",Toast.LENGTH_SHORT);
//                        }
//                        if(task.isSuccessful()){
//                            String type = Dbhelper.login(email,pwd);
//                            if(type == "Manager"){
//                                progressdialog.dismiss();
//                                finish();
//                                Intent in = new Intent(MainActivity.this,ManagerActivity.class);
//                                startActivity(in);
//                            }
//                            else if(type =="Supervisor"){
//                                progressdialog.dismiss();
//                                finish();
//                                Intent in = new Intent(MainActivity.this,SupervisorActivity.class);
//                                startActivity(in);
//                            }
//                            else if(type == "Guest"){
//                                progressdialog.dismiss();
//                                finish();
//                                Intent in = new Intent(MainActivity.this,GuestActivity.class);
//                                //run query fetch user details
//                                User user = new User();
//                                in.putExtra("user", (Parcelable) user);
//                                startActivity(in);
//                            }
//                        }
//
//                    }
//                });
//
//               else {
//                    progressdialog.dismiss();
//                    Toast.makeText(MainActivity.this, "Enter correct information", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
                Intent in = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(in);
            }
        });

//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                startRegistering();
//            }
//        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
    }


    private void startSignIn() {
        final String username = user.getText().toString();
        final String pwd = pwrd.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(MainActivity.this, "Field Empty", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.setMessage("Logging in User...");
            progressDialog.show();
            User u = db.getUser(username, "");
            if (u == null) {
                Toast.makeText(MainActivity.this, "USER DOESN'T EXIST", Toast.LENGTH_SHORT).show();
            }
            else if (u.getPwd().equals(pwd)) {
                // Set active session
                if(!db.getActiveSession().isActive())
                    db.setActiveSession(new Session(u));
                if (u.getUserAccessLevel().equals("1")) {
                    Toast.makeText(MainActivity.this, "YOU ARE MANAGER", Toast.LENGTH_SHORT).show();
                    // MANAGER ACTIVITY
                    finish();
                    startActivity(new Intent(MainActivity.this, ManagerActivity.class));
                }
                if (u.getUserAccessLevel().equals("2")) {
                    Toast.makeText(MainActivity.this, "YOU ARE SUPERVISOR", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(MainActivity.this, SupervisorActivity.class));

                }
                if (u.getUserAccessLevel().equals("3")) {
                    Toast.makeText(MainActivity.this, "YOU ARE GUEST", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, GuestActivity.class));


                }
                if (u.getUserAccessLevel().equals("4")) {
                    Toast.makeText(MainActivity.this, "YOU ARE FDS", Toast.LENGTH_SHORT).show();
                    // FDS ACTIVITY
                }
            }
            else {
                Toast.makeText(MainActivity.this, "Wrong Password...", Toast.LENGTH_SHORT).show();
            }
            progressDialog.dismiss();
        }
    }

//             mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//
//                    if (!task.isSuccessful()) {
//                        Toast.makeText(MainActivity.this, "Signing in problem", Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        for()
//                       if (pwd.equals("manager"))
//                       {
//                           progressDialog.dismiss();
////                           finish();
//                           Intent i = new Intent(getApplicationContext(),ManagerActivity.class);
//                           startActivity(i);
//                       }
//
//                       else if (pwd.equals("supervisor1") || pwd.equals("supervisor2") || pwd.equals("supervisor3"))
//                       {
//                           progressDialog.dismiss();
////                           finish();
//                           Intent i = new Intent(getApplicationContext(),SupervisorActivity.class);
//                           startActivity(i);
//                       }
//                    }
//
//
//                }
//            });
//        }
//    }
//
//    private void startRegistering() {
//        String email = user.getText().toString();
//        String pwd = pwrd.getText().toString();
//
//        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd)) {
//            Toast.makeText(MainActivity.this, "field empty", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(MainActivity.this, "Registered Successfully...", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
//
//        }
//
//       /* else
//        {
//            progressDialog.setMessage("Registering User...");
//            progressDialog.show();
//
//            mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(this,new  OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task)
//                {
//                    if (task.isSuccessful())
//                    {
//                        Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
//                        //startActivity(new Intent(getApplicationContext(),CustomerActivity.class));
//                    }
//                    else
//                    {
//                        Toast.makeText(MainActivity.this,"Could not register....Please try again",Toast.LENGTH_SHORT).show();
//                    }
//
//                    progressDialog.dismiss();
//
//                }
//            });
//        }*/
//    }

}


