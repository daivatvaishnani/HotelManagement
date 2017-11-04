package com.example.shivam.HotelManagement.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shivam.HotelManagement.Activity.EmployeesListActivity;
import com.example.shivam.HotelManagement.Activity.MainActivity;
import com.example.shivam.HotelManagement.Adapters.CustomAdapter;
import com.example.shivam.HotelManagement.DataCollections.User;
import com.example.shivam.HotelManagement.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by I_AM_SHIVANSH on 10/4/2017.
 */

public class ScheduleFragment extends Fragment{

    final String[] employee = {"emp1","emp2","emp3","emp4","emp5","emp6"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_scheduling,container,false);

        ListView listviewemp;

        final ArrayList<User> users = MainActivity.db.getUsers();


        CustomAdapter adapter = new CustomAdapter(getActivity(),users);

        Button b = (Button) rootView.findViewById(R.id.buttonemp);
        listviewemp = (ListView) rootView.findViewById(R.id.listviewitem);


       // listviewemp.setAdapter(adapter);




        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),EmployeesListActivity.class);
                startActivity(i);
            }
        });

        return rootView;
    }

    public ScheduleFragment()
    {

    }

    /*class customAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return employee.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater(null).inflate(R.layout.list_layout, null);
            TextView textview = (TextView)convertView.findViewById(R.id.ename);
            CheckBox checkbox1 = (CheckBox)convertView.findViewById(R.id.checkBox1);
            CheckBox checkbox2 = (CheckBox)convertView.findViewById(R.id.checkBox2);
            CheckBox checkbox3 = (CheckBox)convertView.findViewById(R.id.checkBox3);
            textview.setText(employee[position]);
            return convertView;
        }
    }


//    public ScheduleFragment()
//    {
//
//    }*/

}
