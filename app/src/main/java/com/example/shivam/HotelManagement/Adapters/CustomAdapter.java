package com.example.shivam.HotelManagement.Adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.shivam.HotelManagement.DataCollections.User;
import com.example.shivam.HotelManagement.R;

import java.util.ArrayList;
import android.content.Context;

/**
 * Created by shivam on 3/11/17.
 */

public class CustomAdapter extends ArrayAdapter<User>
{
    public CustomAdapter(Context context, ArrayList<User> users)
    {
        super(context,0,users);
    }

    View thor;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        //thor = convertView;

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout,parent,false);
        }



        return super.getView(position, convertView, parent);
    }
}
