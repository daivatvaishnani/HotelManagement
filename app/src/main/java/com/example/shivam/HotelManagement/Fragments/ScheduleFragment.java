package com.example.shivam.HotelManagement.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.Activity.EmployeesListActivity;
import com.example.shivam.HotelManagement.Activity.MainActivity;
import com.example.shivam.HotelManagement.DataCollections.User;
import com.example.shivam.HotelManagement.R;

import java.util.ArrayList;

/**
 * Created by Yagyansh on 10/4/2017.
 */

public class ScheduleFragment extends Fragment{
    CheckBox shift1,shift2,shift3;
    Button send,cancel;

    final String[] employee = {"emp1","emp2","emp3","emp4","emp5","emp6"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //String[] employees = {"emp1","emp2","emp3","emp4","emp5","emp6"};
        View rootView = inflater.inflate(R.layout.activity_scheduling,container,false);
        //String ual = new String("1");
        final ArrayList<User> users = MainActivity.db.getUsersUnder(MainActivity.db.getActiveSession().getActiveUser().getUserAccessLevel());
        //String[] employees = users.toArray(new String[0]);
        ArrayList<String> employees = new ArrayList<>();
        final ArrayList<String> employeeEmails = new ArrayList<>();
        for(User u : users) {
            employees.add(u.getUserName());
            employeeEmails.add(u.getEmailId());
        }
        shift1 = (CheckBox) rootView.findViewById(R.id.checkBox);
        shift2 = (CheckBox) rootView.findViewById(R.id.checkBox6);
        shift3 = (CheckBox) rootView.findViewById(R.id.checkBox3);

        send = (Button) rootView.findViewById(R.id.send);
        cancel = (Button) rootView.findViewById(R.id.cancel);

        final ListView listView = (ListView) rootView.findViewById(R.id.EmployeeList);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,employees){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);

                return view;
            }
        };

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setVisibility(View.GONE);
                setvisible(shift1,shift2,shift3,send,cancel);
                shift1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(shift1.isChecked()){
                            shift2.setVisibility(View.INVISIBLE);
                            shift3.setVisibility(View.INVISIBLE);
                        }
                        else if(!shift1.isChecked()){
                            shift2.setVisibility(View.VISIBLE);
                            shift3.setVisibility(View.VISIBLE);
                        }
                    }
                });
                shift2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(shift2.isChecked()){
                            shift1.setVisibility(View.INVISIBLE);
                            shift3.setVisibility(View.INVISIBLE);
                        }
                        else if(!shift2.isChecked()){
                            shift1.setVisibility(View.VISIBLE);
                            shift3.setVisibility(View.VISIBLE);
                        }
                    }
                });
                shift3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(shift3.isChecked()){
                            shift2.setVisibility(View.INVISIBLE);
                            shift1.setVisibility(View.INVISIBLE);
                        }
                        else if(!shift3.isChecked()){
                            shift2.setVisibility(View.VISIBLE);
                            shift1.setVisibility(View.VISIBLE);
                        }
                    }
                });
                final String employeeEmail = employeeEmails.get(position);
                final User employee = MainActivity.db.getUser(employeeEmail);
                final String[] employeeShift = {""};
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SmsManager smsManager = SmsManager.getDefault();
                        String phoneNo = employee.getPhoneNo();
                        if(shift1.isChecked()){
                            //schedfule emp
                            MainActivity.db.getUser(employeeEmail).setShift("1");
                            employeeShift[0] = "1";
                            String sms = "your new shift is 1";
                            try{
                                smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                            }catch(Exception e){}
                            setgone(shift1,shift2,shift3,send,cancel);
                            listView.setVisibility(View.VISIBLE);

                            Toast.makeText(getActivity(),
                                    "shift 1 scheduled", Toast.LENGTH_LONG).show();
                        }
                        else if(shift2.isChecked()){
                            //schedfule emp
                            MainActivity.db.getUser(employeeEmail).setShift("2");
                            employeeShift[0] = "2";
                            String sms = "your new shift is 2";
                            try{
                                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                            }catch(Exception e){}
                            setgone(shift1,shift2,shift3,send,cancel);
                            listView.setVisibility(View.VISIBLE);
                            Toast.makeText(getActivity(),
                                    "shift 2 scheduled", Toast.LENGTH_LONG).show();
                        }
                        else if(shift3.isChecked()){
                            //schedfule emp
                            MainActivity.db.getUser(employeeEmail).setShift("3");
                            employeeShift[0] = "3";
                            String sms = "your new shift is 3";
                            try{
                                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                            }catch(Exception e){}
                            setgone(shift1,shift2,shift3,send,cancel);
                            listView.setVisibility(View.VISIBLE);
                            Toast.makeText(getActivity(),
                                    "shift 3 sheduled", Toast.LENGTH_LONG).show();
                        }
                        setEmployeeShift(employeeEmail, employeeShift[0]);
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listView.setVisibility(View.VISIBLE);
                        setgone(shift1,shift2,shift3,send,cancel);
                        shift1.setChecked(false);
                        shift2.setChecked(false);
                        shift3.setChecked(false);
                    }
                });

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
    public void setgone(CheckBox c1 ,CheckBox c2,CheckBox c3,Button b,Button b2){
        c1.setVisibility(View.GONE);
        c2.setVisibility(View.GONE);
        c3.setVisibility(View.GONE);
        b.setVisibility(View.GONE);
        b2.setVisibility(View.GONE);
    }
    public void setvisible(CheckBox c1 ,CheckBox c2,CheckBox c3,Button b,Button b2){
        c1.setVisibility(View.VISIBLE);
        c2.setVisibility(View.VISIBLE);
        c3.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
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
