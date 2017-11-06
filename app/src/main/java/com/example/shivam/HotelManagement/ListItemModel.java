package com.example.shivam.HotelManagement;

/**
 * Created by yagyansh on 11/5/17.
 */

import java.util.ArrayList;


public class ListItemModel {

    String title;
    ArrayList<EdittextValues> arrayList = new ArrayList<>();

    public ListItemModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<EdittextValues> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<EdittextValues> arrayList) {
        this.arrayList = arrayList;
    }
}
