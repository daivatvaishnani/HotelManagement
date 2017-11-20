package com.example.shivam.HotelManagement.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.shivam.HotelManagement.Adapters.CustomListAdapter;
import com.example.shivam.HotelManagement.DataCollections.Item;
import com.example.shivam.HotelManagement.DataCollections.Service;
import com.example.shivam.HotelManagement.EdittextValues;
import com.example.shivam.HotelManagement.ListItemModel;
import com.example.shivam.HotelManagement.R;

import java.util.ArrayList;

/**
 * Created by yagyansh on 11/6/17.
 */

public class FoodActivity extends AppCompatActivity {

    ArrayList<ListItemModel> arrayListf;

    ExpandableListView elvf;
    CustomListAdapter adapterf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        // To get all items pass no parameter
        final ArrayList<Item> itemsList = MainActivity.db.getItems("Food");
        // To retrieve Food items list pass the parameter "Food"
        arrayListf = new ArrayList<>();
        //String[] items = itemsList.toArray(new String[0]);
        for(Item i : itemsList) {
            arrayListf.add(new ListItemModel(i.getItemName() + '\n' + "Rs." + i.getItemPrice()));
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

        elvf = (ExpandableListView)findViewById(R.id.listviewfood);
        adapterf = new CustomListAdapter(FoodActivity.this, arrayListf);
        adapterf.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        elvf.setAdapter(adapterf);

        Button btn = (Button)findViewById(R.id.showfood);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> quantityofItems = new ArrayList<>();
                for(ListItemModel m : arrayListf) {
                    ArrayList<EdittextValues> modellist = m.getArrayList();
                    if(modellist.isEmpty()) {
                        quantityofItems.add("0");
                    }
                    else {
                        quantityofItems.add(modellist.get(0).getValue());
                    }
                }
                String all = "";
                String roomID = "1";
                String roomType = "1";
                MainActivity.db.AddServiceToBill(roomID, roomType, "Food", itemsList, quantityofItems);
                try {
                    Service s = MainActivity.db.getActiveSession().getActiveUser().getBookings().get(0).getBill().getLastService();
                    for(Item i : s.getItems()) {
                        all += "Item : " + i.getItemName() + " ItemQuantity : " + i.getItemQuantity() + "\n";
                    }
                    all += "Total Service Amount : " + s.getServiceAmount() + "\n";
                    Toast.makeText(FoodActivity.this, all, Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(FoodActivity.this, "No booking found!", Toast.LENGTH_SHORT).show();
                }
//                for (int i=0; i<arrayListf.size(); i++){
//                    for (int j=0; j<arrayListf.get(i).getArrayList().size(); j++) {
//                        all += arrayListf.get(i).getArrayList().get(j).getValue() + "\n";
//                    }
//                }
                Toast.makeText(FoodActivity.this, all, Toast.LENGTH_LONG).show();
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        if (id == R.id.action_settings_food) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}