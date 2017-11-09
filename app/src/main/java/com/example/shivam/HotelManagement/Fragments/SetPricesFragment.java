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
    static final int DIALOG_ID = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.setprice,container,false);
      //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);



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

        setPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.db.setSingleRoomPrice(singleP.getText().toString());
                MainActivity.db.setDoubleRoomPrice(doubleP.getText().toString());
                MainActivity.db.setDeluxeRoomPrice(deluxeP.getText().toString());

                Toast.makeText(getActivity().getApplicationContext(), "Prices are set", Toast.LENGTH_SHORT).show();
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
        month_y = month;
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
}
