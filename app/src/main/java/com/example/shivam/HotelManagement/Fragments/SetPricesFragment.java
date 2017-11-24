package com.example.shivam.HotelManagement.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.Activity.DateActivity;
import com.example.shivam.HotelManagement.Activity.GuestActivity;
import com.example.shivam.HotelManagement.Activity.MainActivity;
import com.example.shivam.HotelManagement.R;

import org.w3c.dom.Text;

/**
 * Created by I_AM_SHIVANSH on 10/4/2017.
 */

public class SetPricesFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    TextView start,end;
    int year_x,month_x,day_x;
    int year_y,month_y,day_y;
    static int indate,inmonth,inyear;
    static int outdate,outmonth,outyear;
    static final int DIALOG_ID = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.setprice,container,false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        start = (TextView) rootView.findViewById(R.id.startdate);
        end =  (TextView) rootView.findViewById(R.id.enddate);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year_x = c.get(Calendar.YEAR);
                month_x = c.get(Calendar.MONTH);
                day_x = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),SetPricesFragment.this,
                        year_x,month_x,day_x);
                datepickerdialog.show();
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year_y = c.get(Calendar.YEAR);
                month_y = c.get(Calendar.MONTH);
                day_y = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),SetPricesFragment.this,
                        year_y,month_y,day_y);
                datepickerdialog.show();
            }
        });


        //Button logOut = (Button) rootView.findViewById(R.id.logOut);
        Button setPrice = (Button) rootView.findViewById(R.id.price);
        final EditText singleP = (EditText) rootView.findViewById(R.id.singleprice);
        final EditText doubleP = (EditText) rootView.findViewById(R.id.doubleprice);
        final EditText deluxeP = (EditText) rootView.findViewById(R.id.deluxeprice);
        final EditText discount = (EditText) rootView.findViewById(R.id.discount);

        setPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String in = start.getText().toString();
                String out = end.getText().toString();

                Boolean datestat = checkdate(in,out);
                if(!datestat){
                    Toast.makeText(getActivity(), "enter dates correctly", Toast.LENGTH_SHORT).show();
                }
                if(singleP.getText().toString().equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "enter price for single room", Toast.LENGTH_SHORT).show();
                }
                else if(doubleP.getText().toString().equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "enter price for double room", Toast.LENGTH_SHORT).show();
                }
                else if(deluxeP.getText().toString().equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "enter price for deluxe room", Toast.LENGTH_SHORT).show();
                }
                else {
                    MainActivity.db.setSingleRoomPrice(singleP.getText().toString());
                    MainActivity.db.setDoubleRoomPrice(doubleP.getText().toString());
                    MainActivity.db.setDeluxeRoomPrice(deluxeP.getText().toString());

                    Toast.makeText(getActivity().getApplicationContext(), "Prices are set", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
            }
        });*/

        return rootView;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        year_x = year;
        month_x = month + 1;
        day_x = dayOfMonth;
        year_y = year;
        month_y = month + 1;
        day_y = dayOfMonth;

        String x = start.getText().toString();

        if (x.equals("Start Date"))
        {
            start.setText(day_x + "/" + month_x + "/" + year_x) ;
        }
        else
        {
            end.setText(day_y + "/" + month_y + "/" + year_y) ;
        }
    }

    Boolean checkdate(String in, String out){
        int flag = 0;
        if(in.length()==10){
            inyear = Integer.parseInt(in.substring(6,10));
            indate = Integer.parseInt(in.substring(0,2));
            inmonth = Integer.parseInt(in.substring(3,5));
        }
        if(in.length()==9){
            indate = Integer.parseInt(in.substring(0,1));
            inmonth = Integer.parseInt(in.substring(2,4));
            inyear = Integer.parseInt(in.substring(5,9));
        }
        if(out.length()==10){
            outdate = Integer.parseInt(out.substring(0,2));
            outmonth = Integer.parseInt(out.substring(3,5));
            outyear = Integer.parseInt(out.substring(6,10));
        }
        if(out.length()==9){
            outdate = Integer.parseInt(out.substring(0,1));
            outmonth = Integer.parseInt(out.substring(2,4));
            outyear = Integer.parseInt(out.substring(5,9));
        }
        System.out.println(indate + " " + inmonth + " " + inyear);
        System.out.println(outdate + " " + outmonth + " " + outyear);

        if(outyear>inyear) flag = 1;
        else if(outyear==inyear){
            if(outmonth>inmonth) flag=1;
            else if(outmonth==inmonth){
                if(outdate>=indate) flag=1;
            }
        }
        System.out.println(flag);
        if(flag == 1) return true;
        else
            return false;
    }
}
