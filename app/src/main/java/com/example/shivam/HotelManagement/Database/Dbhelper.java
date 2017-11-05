package com.example.shivam.HotelManagement.Database;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.shivam.HotelManagement.Activity.MainActivity;
import com.example.shivam.HotelManagement.DataCollections.*;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by shivam on 9/10/17.
 */

public class Dbhelper {
    private static FirebaseAuth mAuth;
    public static DatabaseReference mRef;

   // Firebase firebase = new Firebase(config.url);

    public static boolean add_guest(User user){
        try{

            //firebase.child("User").setValue(user);
            return true;
        }catch(Exception e){ return false;}
    }

    public static String login(String email,String pwd){
            try{
                Query query = mRef.orderByChild("email").equalTo(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // There may be multiple users with the email address, so we need to loop over the matches
                        for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                            System.out.println(userSnapshot.getKey());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                       // Log.w(TAG, "find email address:onCancelled", databaseError.toException());
                        // ...
                    }
                });
                return "okay";
            }catch(Exception e){ return "error";}
    }
    public static boolean setprices(int a,int b,int c){
        try{


            return true;
        }catch(Exception e){ return false;}
    }

    public static boolean schedulesupervisor(){
        try{
            return true;
        }catch(Exception e){ return false;}
    }

    public static boolean bookroom(){
        try{
            return true;
        }catch(Exception e){ return false;}
    }
}
