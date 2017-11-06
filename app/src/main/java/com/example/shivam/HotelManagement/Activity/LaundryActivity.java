package com.example.shivam.HotelManagement.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.DataCollections.Item;
import com.example.shivam.HotelManagement.DataCollections.User;
import com.example.shivam.HotelManagement.ListItemModel;
import com.example.shivam.HotelManagement.Adapters.CustomListAdapter;
import com.example.shivam.HotelManagement.R;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;


public class LaundryActivity extends AppCompatActivity {

    ArrayList<ListItemModel> arrayList;

    ExpandableListView elv;
    CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);
        // To get all items pass no parameter
        ArrayList<Item> itemsList = MainActivity.db.getItems("Laundry");
        // To retrieve Food items list pass the parameter "Food"
        arrayList = new ArrayList<>();
        //String[] items = itemsList.toArray(new String[0]);

        arrayList.add(new ListItemModel("Jeans\nRs10"));
        arrayList.add(new ListItemModel("Shirt\nRs10"));
        arrayList.add(new ListItemModel("T-Shirt\nRs10"));
        arrayList.add(new ListItemModel("Trouser\nRs10"));
        arrayList.add(new ListItemModel("Towel\nRs10"));
        arrayList.add(new ListItemModel("Tux\nRs10"));
        arrayList.add(new ListItemModel("Blah\nRs10"));
        arrayList.add(new ListItemModel("Blah\nRs10"));
        arrayList.add(new ListItemModel("Blah.3\nRs10"));
        //arrayList.add(new ListItemModel("Title 10"));

        elv = (ExpandableListView)findViewById(R.id.listview);
        adapter = new CustomListAdapter(LaundryActivity.this, arrayList);
        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        elv.setAdapter(adapter);

        Button btn = (Button)findViewById(R.id.show);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String all="";
                for (int i=0; i<arrayList.size(); i++){
                    for (int j=0; j<arrayList.get(i).getArrayList().size(); j++) {
                        all += arrayList.get(i).getArrayList().get(j).getValue() + "\n";
                    }
                }
                Toast.makeText(LaundryActivity.this, all, Toast.LENGTH_LONG).show();
            }
        });
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            if( bundle.getString("some") !=  null){
                Toast.makeText(getApplicationContext(), "Data:" + bundle.getString("some"), Toast.LENGTH_SHORT ).show();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds groupItem to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
