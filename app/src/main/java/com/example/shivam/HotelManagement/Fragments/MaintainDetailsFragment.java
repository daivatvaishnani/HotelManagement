package com.example.shivam.HotelManagement.Fragments;

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
        final ListView listView = (ListView) rootView.findViewById(R.id.EmployeeList);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,employees);
        listView.setAdapter(listViewAdapter);

        editid = (TextView) rootView.findViewById(R.id.editid);
        editpassword = (TextView) rootView.findViewById(R.id.editpassword);
        edittype = (TextView) rootView.findViewById(R.id.edittype);
        editphno = (TextView) rootView.findViewById(R.id.editphno);
        setedit = (Button) rootView.findViewById(R.id.setedit);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setVisibility(View.GONE);
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
