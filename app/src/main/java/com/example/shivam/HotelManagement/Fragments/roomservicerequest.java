package com.example.shivam.HotelManagement.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shivam.HotelManagement.R;

/**
 * Created by shivam on 28/10/17.
 */

public class roomservicerequest extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_employeedetails,container,false);

        return rootView;
    }
}
