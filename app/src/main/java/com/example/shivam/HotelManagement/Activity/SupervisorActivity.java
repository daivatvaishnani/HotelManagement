package com.example.shivam.HotelManagement.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.shivam.HotelManagement.Fragments.SimpleFragmentPagerAdapter;
import com.example.shivam.HotelManagement.Fragments.SimpleFragmentPagerAdapterSup;
import com.example.shivam.HotelManagement.R;

public class SupervisorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagersup);

        SimpleFragmentPagerAdapterSup simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapterSup(this,getSupportFragmentManager());

        viewPager.setAdapter(simpleFragmentPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_layouts_sup);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds groupItem to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            startActivity(new Intent(SupervisorActivity.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
