package com.example.shivam.HotelManagement.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shivam.HotelManagement.DataCollections.User;
import com.example.shivam.HotelManagement.R;

public class PaymentActivity extends AppCompatActivity {
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

        String card = user.getCardNo();

        /*if(!card.isEmpty()){
            cardno.setText(card);
        }*/

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String card_no = cardno.getText().toString();
                String cvv_no = cvv.getText().toString();
                String expire_date = expire.getText().toString();
                Boolean check = card_no.length()==16;
                if(card_no.length()==0){
                    Toast.makeText(PaymentActivity.this, "Please enter card no", Toast.LENGTH_SHORT).show();
                }
                else if(!check){
                    Toast.makeText(PaymentActivity.this, "invalid card no", Toast.LENGTH_SHORT).show();
                }
                check = cvv_no.length()==3;
                if(!check){
                    Toast.makeText(PaymentActivity.this, "invalid cvv no", Toast.LENGTH_SHORT).show();
                }
                else if(card_no.length()==0){
                    Toast.makeText(PaymentActivity.this, "Please enter cvv no", Toast.LENGTH_SHORT).show();
                }
                else if(expire_date.length()==0){
                    Toast.makeText(PaymentActivity.this, "Please enter expire date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(getApplicationContext(),
                            GuestActivity.class);
                    startActivity(i);
                }
            }

        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        final AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
        builder.setMessage("Are you sure you want to exit?");
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
