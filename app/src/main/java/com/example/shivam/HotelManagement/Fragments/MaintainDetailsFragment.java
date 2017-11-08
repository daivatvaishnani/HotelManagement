package com.example.shivam.HotelManagement.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.Activity.MainActivity;
import com.example.shivam.HotelManagement.DataCollections.User;
import com.example.shivam.HotelManagement.R;

import java.util.ArrayList;

/**
 * Created by I_AM_SHIVANSH on 10/5/2017.
 */

public class MaintainDetailsFragment extends Fragment{

    TextView editid,editname,editpassword,edittype,editphno;
    Button setedit;
    AlertDialog dialog;
    EditText edittext;
    static int editvalue=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_employeedetails,container,false);

        final ArrayList<User> users = MainActivity.db.getUsers();
//        String[] employees = users.toArray(new String[0]);
        ArrayList<String> employees = new ArrayList<>();
        for(User u : users) {
            employees.add(u.getUserName());
        }
        final ListView listView = (ListView) rootView.findViewById(R.id.slist);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,employees);
        listView.setAdapter(listViewAdapter);

        editid = (TextView) rootView.findViewById(R.id.editid);
        editpassword = (TextView) rootView.findViewById(R.id.editpassword);
        edittype = (TextView) rootView.findViewById(R.id.edittype);
        editphno = (TextView) rootView.findViewById(R.id.editphno);
        setedit = (Button) rootView.findViewById(R.id.setedit);

        dialog = new AlertDialog.Builder(getContext()).create();
        edittext = new EditText(getContext());

        dialog.setTitle("Edit The text");
        dialog.setView(edittext);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listView.setVisibility(View.GONE);
                String s = listView.getItemAtPosition(position).toString();

                setvisible(editid, editname, editpassword, editphno, edittype, setedit);

                // retrieve the details of emp of name "s"
                //MainActivity.db.
                editid.setText("hello");
                editname.setText("shivam");
                editpassword.setText("sdsq");
                edittype.setText("fds");
                editphno.setText("95587");

                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save Changes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editvalue == 1)
                            editid.setText(edittext.getText());
                        else if (editvalue == 2)
                            editname.setText(edittext.getText());
                        else if (editvalue == 3)
                            editpassword.setText(edittext.getText());
                        else if (editvalue == 4)
                            edittype.setText(edittext.getText());
                        else if (editvalue == 5)
                            editphno.setText(edittext.getText());
                    }
                });


               editid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editvalue = 1;
                edittext.setText(editid.getText());
                dialog.show();
            }
        });

        editid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editvalue = 1;
                edittext.setText(editid.getText());
                dialog.show();
            }
        });

        editname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editvalue = 2;
                edittext.setText(editname.getText());
                dialog.show();
            }
        });

        editpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editvalue = 3;
                edittext.setText(editpassword.getText());
                dialog.show();
            }
        });

        edittype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editvalue = 4;
                edittext.setText(edittype.getText());
                dialog.show();
            }
        });

        editphno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editvalue = 5;
                edittext.setText(editphno.getText());
                dialog.show();
            }
        });

        setedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //run query for setting changes
                Toast.makeText(getContext(), "Confirmed set", Toast.LENGTH_SHORT).show();
                setgone(editid, editname, editpassword, editphno, edittype, setedit);
                listView.setVisibility(View.VISIBLE);
            }

        });

            }
        });

        return rootView;
    }
    public void setgone(TextView editid,TextView editname,TextView editpassword,
                           TextView editphno,TextView edittype,Button setedit){
        editid.setVisibility(View.GONE);
        editname.setVisibility(View.GONE);
        editpassword.setVisibility(View.GONE);
        editphno.setVisibility(View.GONE);
        edittype.setVisibility(View.GONE);
        setedit.setVisibility(View.GONE);
    }
    public void setvisible(TextView editid,TextView editname,TextView editpassword,
                           TextView editphno,TextView edittype,Button setedit){
        editid.setVisibility(View.VISIBLE);
        editname.setVisibility(View.VISIBLE);
        editpassword.setVisibility(View.VISIBLE);
        editphno.setVisibility(View.VISIBLE);
        edittype.setVisibility(View.VISIBLE);
        setedit.setVisibility(View.VISIBLE);
    }
}
