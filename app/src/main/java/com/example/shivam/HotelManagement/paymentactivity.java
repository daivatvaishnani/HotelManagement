package com.example.shivam.HotelManagement;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shivam.HotelManagement.Activity.MainActivity;
import com.example.shivam.HotelManagement.DataCollections.User;

public class paymentactivity extends AppCompatActivity {
    EditText cardno,cvv,expire;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentactivity);

        cardno = (EditText)findViewById(R.id.cardno);
        cvv = (EditText)findViewById(R.id.cvvno);
        expire = (EditText)findViewById(R.id.expire);

        confirm = (Button) findViewById(R.id.confirmpay);

        User user = MainActivity.db.getActiveSession().getActiveUser();

        String card = user.getCard_no();

        /*if(!card.isEmpty()){
            cardno.setText(card);
        }*/

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String card_no = cardno.getText().toString();
                String cvv_no = cvv.getText().toString();
                String expire_date = expire.getText().toString();

                /*if(cvv_no.isEmpty()){

                }*/

            }

        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        final AlertDialog.Builder builder = new AlertDialog.Builder(paymentactivity.this);
        builder.setMessage("Are you sure you want to exit");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
