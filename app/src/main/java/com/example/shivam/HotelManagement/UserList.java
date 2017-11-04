/*
package com.example.shivam.HotelManagement;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.shivam.HotelManagement.DataCollections.*;

import java.util.List;

*/
/**
 * Created by shivam on 10/10/17.
 *//*


public class UserList extends ArrayAdapter<User> {
    private Activity context;
    private List<User> empList;


    public UserList(Activity context, List<User> empList) {
        super(context, R.layout.list_layout,empList);
        this.context = context;
        this.empList = empList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator = context.getLayoutInflater();

        View listviewitem = inflator.inflate(R.layout.list_layout,null,true);

        TextView ename = (TextView) listviewitem.findViewById(R.id.ename);
        CheckBox shift1 = (CheckBox) listviewitem.findViewById(R.id.checkBox1);
        CheckBox shift2 = (CheckBox) listviewitem.findViewById(R.id.checkBox1);
        CheckBox shift3 = (CheckBox) listviewitem.findViewById(R.id.checkBox1);

        User user = empList.get(position);

        ename.setText(user.getName());

        return listviewitem;
     }
}
*/
