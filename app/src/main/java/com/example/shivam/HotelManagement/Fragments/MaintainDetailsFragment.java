package com.example.shivam.HotelManagement.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shivam.HotelManagement.R;

/**
 * Created by I_AM_SHIVANSH on 10/5/2017.
 */

public class MaintainDetailsFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_employeedetails,container,false);

        return rootView;
    }
}
