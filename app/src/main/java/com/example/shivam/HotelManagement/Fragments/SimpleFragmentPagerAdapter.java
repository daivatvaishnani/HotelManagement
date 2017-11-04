package com.example.shivam.HotelManagement.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by I_AM_SHIVANSH on 10/4/2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private static String[] tabtitles = new String[] {"Prices","Schedule","Details"};

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context,FragmentManager fm)
    {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
        {
            return new SetPricesFragment();
        }

        else if(position == 1)
        {
            return new ScheduleFragment();
        }

        else
        {
            return new MaintainDetailsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position)
    {
        return tabtitles[position];
    }
}
