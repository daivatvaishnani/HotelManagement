package com.example.shivam.HotelManagement.Activity;


import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.DataCollections.Item;
import com.example.shivam.HotelManagement.DataCollections.Service;
import com.example.shivam.HotelManagement.DataCollections.User;
import com.example.shivam.HotelManagement.EdittextValues;
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
        final ArrayList<Item> itemsList = MainActivity.db.getItems("Laundry");
        // To retrieve Food items list pass the parameter "Food"
        arrayList = new ArrayList<>();
        //String[] items = itemsList.toArray(new String[0]);
        for(Item i : itemsList) {
            arrayList.add(new ListItemModel(i.getItemName() + '\n' + "Rs." + i.getItemPrice()));
        }
//        arrayList.add(new ListItemModel("Jeans\nRs10"));
//        arrayList.add(new ListItemModel("Shirt\nRs10"));
//        arrayList.add(new ListItemModel("T-Shirt\nRs10"));
//        arrayList.add(new ListItemModel("Trouser\nRs10"));
//        arrayList.add(new ListItemModel("Towel\nRs10"));
//        arrayList.add(new ListItemModel("Tux\nRs10"));
//        arrayList.add(new ListItemModel("Blah\nRs10"));
//        arrayList.add(new ListItemModel("Blah\nRs10"));
//        arrayList.add(new ListItemModel("Blah.3\nRs10"));
        //arrayList.add(new ListItemModel("Title 10"));

        elv = (ExpandableListView)findViewById(R.id.listview);
        adapter = new CustomListAdapter(LaundryActivity.this, arrayList);
        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        elv.setAdapter(adapter);
        final String room_type,room_no;
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            room_type = null;
            room_no =  null;
        } else {
            room_type = extras.getString("roomtype");
            room_no = extras.getString("roomno");
        }

        //final long mLastClickTime = 0;
        final Button btn = (Button)findViewById(R.id.show);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //btn.setEnabled(false);
                // mis-clicking prevention, using threshold of 1000 ms
                /*if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime(); */

                // do your magic here
                ArrayList<String> quantityOfItems = new ArrayList<>();
                for(ListItemModel m : arrayList) {
                    ArrayList<EdittextValues> modelList = m.getArrayList();
                    if(modelList.isEmpty()) {
                        quantityOfItems.add("0");
                    }
                    else {
                        quantityOfItems.add(modelList.get(0).getValue());
                    }
                }
                final String room_type,room_no;
                Bundle extras = getIntent().getExtras();
                if(extras == null) {
                    room_type = null;
                    room_no =  null;
                } else {
                    room_type = extras.getString("roomtype");
                    room_no = extras.getString("roomno");
                    System.out.println(room_no);
                    System.out.println(room_type);
                }

                String all = "";
                String roomID = room_no;
                String roomType = "";
                if(room_type.equals("Single")){roomType = "1";}
                else if(room_type.equals("Double")){ roomType = "2";}
                else if(room_type.equals("Deluxe")){roomType = "3";}
                System.out.println(roomID);
                System.out.println(roomType);
                try {
                    MainActivity.db.AddServiceToBill(roomID, roomType, "Laundry", itemsList, quantityOfItems);
                    User user = MainActivity.db.getActiveSession().getActiveUser();
                    System.out.println("hiiiiiiiiii");
                    System.out.println(user.getEmailId());
                    System.out.println(user.getUserName());
                    Service s = MainActivity.db.getActiveSession().getActiveUser().getBookings().get(0).getBill().getLastService();
                    for(Item i : s.getItems()) {
                        all += "Item : " + i.getItemName() + " ItemQuantity : " + i.getItemQuantity() + "\n";
                    }
                    all += "Total Service Amount : " + s.getServiceAmount() + "\n";
                    Toast.makeText(LaundryActivity.this, all, Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(LaundryActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
//                for(int i=0; i<arrayList.size(); i++){
//                    for (int j=0; j<arrayList.get(i).getArrayList().size(); j++) {
//                        all += arrayList.get(i).getArrayList().get(j).getValue() + "\n";
//                    }
//                }
//                Toast.makeText(LaundryActivity.this, all, Toast.LENGTH_LONG).show();
            }
        });

        //Bundle bundle = getIntent().getExtras();
        /*if(bundle!=null){
            if( bundle.getString("some") !=  null){
                Toast.makeText(getApplicationContext(), "Data:" + bundle.getString("some"), Toast.LENGTH_SHORT ).show();
            }
        }*/

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds groupItem to the action bar if it is present.
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    }*/
}
