package com.example.shivam.HotelManagement.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.Activity.EmployeesListActivity;
import com.example.shivam.HotelManagement.Activity.MainActivity;
import com.example.shivam.HotelManagement.DataCollections.User;
import com.example.shivam.HotelManagement.R;

import java.util.ArrayList;

/**
 * Created by I_AM_SHIVANSH on 10/4/2017.
 */

public class ScheduleFragment extends Fragment{
    CheckBox shift1,shift2,shift3;

    final String[] employee = {"emp1","emp2","emp3","emp4","emp5","emp6"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //String[] employees = {"emp1","emp2","emp3","emp4","emp5","emp6"};
        View rootView = inflater.inflate(R.layout.activity_scheduling,container,false);
        //String ual = new String("1");
        final ArrayList<User> users = MainActivity.db.getUsers();
//        String[] employees = users.toArray(new String[0]);
        ArrayList<String> employees = new ArrayList<>();
        final ArrayList<String> employeeEmails = new ArrayList<>();
        for(User u : users) {
            employees.add(u.getUserName());
            employeeEmails.add(u.getEmailId());
        }
        shift1 = (CheckBox) rootView.findViewById(R.id.checkBox);
        shift2 = (CheckBox) rootView.findViewById(R.id.checkBox6);
        shift3 = (CheckBox) rootView.findViewById(R.id.checkBox3);

        final ListView listView = (ListView) rootView.findViewById(R.id.EmployeeList);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,employees);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setVisibility(View.GONE);
                setvisible(shift1,shift2,shift3);
                String employeeEmail = employeeEmails.get(position);
                String employeeShift = "";
                if(shift1.isChecked()){
                    //schedfule emp
                    employeeShift = "1";
                    setgone(shift1,shift2,shift3);
                    listView.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(),
                            "shift 1 scheduled", Toast.LENGTH_LONG).show();
                }
                else if(shift2.isChecked()){
                    //schedfule emp
                    employeeShift = "2";
                    setgone(shift1,shift2,shift3);
                    listView.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(),
                            "shift 2 scheduled", Toast.LENGTH_LONG).show();
                }
                else if(shift3.isChecked()){
                    //schedfule emp
                    employeeShift = "3";
                    setgone(shift1,shift2,shift3);
                    listView.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(),
                            "shift 3 sheduled", Toast.LENGTH_LONG).show();
                }
                setEmployeeShift(employeeEmail, employeeShift);
            }
        });



        //CustomAdapter adapter = new CustomAdapter(getActivity(),users);

//        Button b = (Button) rootView.findViewById(R.id.buttonemp);
        //listviewemp = (ListView) rootView.findViewById(R.id.listviewitem);


       // listviewemp.setAdapter(adapter);




//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(),EmployeesListActivity.class);
//                startActivity(i);
//            }
//        });

        return rootView;
    }
    public void setgone(CheckBox c1 ,CheckBox c2,CheckBox c3){
        c1.setVisibility(View.GONE);
        c2.setVisibility(View.GONE);
        c3.setVisibility(View.GONE);
    }
    public void setvisible(CheckBox c1 ,CheckBox c2,CheckBox c3){
        c1.setVisibility(View.VISIBLE);
        c2.setVisibility(View.VISIBLE);
        c3.setVisibility(View.VISIBLE);
    }

    public void setEmployeeShift(String employeeEmail, String employeeShift) {
        MainActivity.db.getUser(employeeEmail).setShift(employeeShift);
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
