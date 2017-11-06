package com.example.shivam.HotelManagement.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shivam.HotelManagement.Activity.LaundryActivity;
import com.example.shivam.HotelManagement.R;

/**
 * Created by I_AM_SHIVANSH on 10/4/2017.
 */

public class RoomServiceSupervisor extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_service, container, false);
        Button laundrybutton = (Button) rootView.findViewById(R.id.laundrybutton);
        laundrybutton.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
                        Intent in = new Intent(getActivity(), LaundryActivity.class);
                        in.putExtra("Some" , "Some Data");
                        startActivity(in);
            }

        });

        Button foodbutton = (Button) rootView.findViewById(R.id.foodbutton);
        laundrybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), LaundryActivity.class);
                in.putExtra("Some" , "Some Data");
                startActivity(in);
            }

        });
        return rootView;
    }
}

