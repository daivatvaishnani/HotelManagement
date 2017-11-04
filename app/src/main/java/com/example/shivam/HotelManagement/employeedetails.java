package com.example.shivam.HotelManagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.Activity.MainActivity;
import com.example.shivam.HotelManagement.Activity.RegisterActivity;
import com.example.shivam.HotelManagement.DataCollections.*;

import org.w3c.dom.Text;

import java.util.List;

public class employeedetails extends AppCompatActivity {

//    TextView editid,editname,editpassword,edittype,editphno;
//    Button setedit;
//    AlertDialog dialog;
//    EditText edittext;
//    static int editvalue=0;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_employeedetails);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        editid = (TextView) findViewById(R.id.editid);
//        editname = (TextView) findViewById(R.id.editname);
//        editpassword = (TextView) findViewById(R.id.editpassword);
//        edittype = (TextView) findViewById(R.id.edittype);
//        editphno = (TextView) findViewById(R.id.editphno);
//        setedit = (Button) findViewById(R.id.setedit);
//
//        dialog = new AlertDialog.Builder(this).create();
//        edittext = new EditText(this);
//
//        dialog.setTitle("Edit The text");
//        dialog.setView(edittext);
//
//        dialog.setButton(DialogInterface.BUTTON_POSITIVE,"Save Changes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if(editvalue == 1)
//                    editid.setText(edittext.getText());
//                else if(editvalue == 2)
//                    editname.setText(edittext.getText());
//                else if(editvalue == 3)
//                    editpassword.setText(edittext.getText());
//                else if(editvalue == 4)
//                    edittype.setText(edittext.getText());
//                else if(editvalue == 5)
//                    editphno.setText(edittext.getText());
//            }
//        });
//
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent in = new Intent(employeedetails.this,Addstaff.class);
//                startActivity(in);
//            }
//        });
//
//        String[] listArray ={"shivam", "yagyansh", "daivat", "shivansh",
//                "shivam", "yagyansh", "daivat", "shivansh", "shivam", "yagyansh", "daivat", "shivansh",};
//
//        ArrayAdapter adapter = new ArrayAdapter<String>(this,
//                R.layout.word_list, listArray);
//
//        final ListView listView = (ListView) findViewById(R.id.slist);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                listView.setVisibility(View.GONE);
//                String s = listView.getItemAtPosition(position).toString();
//                //run query for finding the employee details
//                /*user.setId();
//                user.setName();
//                user.setPwd();    //set values
//                user.setType();
//                user.setPhno();*/
//                User user =  new User();
//
//                editid.setText(user.getId());
//                editname.setText(user.getName());
//                editpassword.setText(user.getPwd());
//                edittype.setText(user.getType());
//                editphno.setText(user.getPhno());
//
//                editid.setVisibility(View.VISIBLE);
//                editname.setVisibility(View.VISIBLE);
//                editpassword.setVisibility(View.VISIBLE);
//                edittype.setVisibility(View.VISIBLE);
//                editphno.setVisibility(View.VISIBLE);
//            }
//        });
//
//        editid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editvalue = 1;
//                edittext.setText(editid.getText());
//                dialog.show();
//            }
//        });
//
//        editid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editvalue = 1;
//                edittext.setText(editid.getText());
//                dialog.show();
//            }
//        });
//
//        editname.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editvalue = 2;
//                edittext.setText(editname.getText());
//                dialog.show();
//            }
//        });
//
//        editpassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editvalue = 3;
//                edittext.setText(editpassword.getText());
//                dialog.show();
//            }
//        });
//
//        edittype.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editvalue = 4;
//                edittext.setText(edittype.getText());
//                dialog.show();
//            }
//        });
//
//        editphno.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editvalue = 5;
//                edittext.setText(editphno.getText());
//                dialog.show();
//            }
//        });
//
//        setedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //run query for setting changes
//                Toast.makeText(getApplicationContext(),"Confirmed set",Toast.LENGTH_SHORT).show();
//                editid.setVisibility(View.GONE);
//                editname.setVisibility(View.GONE);
//                editpassword.setVisibility(View.GONE);
//                edittype.setVisibility(View.GONE);
//                editphno.setVisibility(View.GONE);
//                listView.setVisibility(View.VISIBLE);
//            }
//        });
//
//    }
//
}
