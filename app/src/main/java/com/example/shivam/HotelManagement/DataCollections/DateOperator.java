package com.example.shivam.HotelManagement.DataCollections;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lonewolf on 19/11/17.
 */

public class DateOperator {

    public Date stringToDate(String Date, char sep) throws ParseException {
        if(Date.length() == 9) {
            DateFormat format = new SimpleDateFormat("d" + sep + "mm" + sep + "yyyy");
            Date date = format.parse(Date);
            return date;
        }
        DateFormat format = new SimpleDateFormat("dd" + sep + "mm" + sep + "yyyy");
        Date date = format.parse(Date);
        return date;
    }

    public String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        return dateFormat.format(date);
    }

}


//    DateOperator dop = new DateOperator();
//    Date checkInDate = new Date();
//    Date checkOutDate = new Date();
//                    try {
//                            checkInDate = dop.stringToDate(in, '-');
//                            checkOutDate = dop.stringToDate(out, '-');
//                            } catch (ParseException e) {
//                            e.printStackTrace();
//                            }
//                   Toast.makeText(GuestActivity.this, dop.dateToString(checkInDate), Toast.LENGTH_SHORT).show();
//                            if(nosingle.length() < 1) {nosingle = "0";}
//        if(nodouble.length() < 1) {nodouble = "0";}
//        if(nodeluxe.length() < 1) {nodeluxe = "0";}
//                    String info = nosingle + " " + nodouble + " " + nodeluxe;
//                    Toast.makeText(GuestActivity.this, info, Toast.LENGTH_LONG).show();
//        int status = MainActivity.db.tryBooking(user.getUserName(), checkInDate, checkOutDate, nosingle, nodouble, nodeluxe);
//
//        ArrayList<Booking> bookings = user.getBookings();
//        Toast.makeText(GuestActivity.this, Integer.toString(bookings.size()), Toast.LENGTH_SHORT).show();
//                    if(status == 1) {
//                       Toast.makeText(GuestActivity.this, "Single Rooms are not available", Toast.LENGTH_SHORT).show();
//                    }
//                    else if(status == 2) {
//                       Toast.makeText(GuestActivity.this, "Double Rooms are not available", Toast.LENGTH_SHORT).show();
//
//                    }
//                    else if(status == 3) {
//                       Toast.makeText(GuestActivity.this, "Deluxe Rooms are not available", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        ArrayList<Booking> bookings = MainActivity.db.getActiveSession().getActiveUser().getBookings();
//                        Booking b = bookings.get(0);
//                        ArrayList<Room> brooms = b.getRooms();
//                        String rooms = "";
//                        for (Room r : brooms) {
//                            rooms.concat(r.getRoomID() + " - " + r.getRoomType() + "\n");
//                        }
//                        Toast.makeText(GuestActivity.this, Integer.toString(brooms.size()), Toast.LENGTH_LONG).show();
//                    }