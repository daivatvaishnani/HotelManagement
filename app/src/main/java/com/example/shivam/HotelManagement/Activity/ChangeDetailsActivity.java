package com.example.shivam.HotelManagement.Activity;


        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.shivam.HotelManagement.DataCollections.User;
        import com.example.shivam.HotelManagement.R;

        import java.util.ArrayList;

public class ChangeDetailsActivity extends AppCompatActivity {

    Button btnUsername,btnPassword,btnEmail,btnPhoneNumber;
    TextView textInfo;
    User activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changedetails);

        // Getting active user
        activeUser = MainActivity.db.getActiveSession().getActiveUser();

        btnUsername = (Button) findViewById(R.id.username);
        btnEmail = (Button) findViewById(R.id.email);
        btnPassword = (Button) findViewById(R.id.passwordc);
        btnPhoneNumber = (Button) findViewById(R.id.phoneno);
        textInfo = (TextView) findViewById(R.id.info);

        final ArrayList<User> users = MainActivity.db.getUsers();

        btnUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(1);
            }
        });
        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(2);
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(3);
            }
        });
        btnPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(4);
            }
        });
    }

    private void openDialog(int key) {
        LayoutInflater inflater = LayoutInflater.from(ChangeDetailsActivity.this);
        View subView = inflater.inflate(R.layout.popup_layout, null);
        final EditText subEditText = (EditText) subView.findViewById(R.id.dialogEditText);
        //final ImageView subImageView = (ImageView)subView.findViewById(R.id.image);
        //Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        //subImageView.setImageDrawable(drawable);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (key == 1) { //Username
            builder.setTitle("Change Username");
            builder.setMessage("Current Username");
            builder.setView(subView);
            AlertDialog alertDialog = builder.create();

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    textInfo.setText(subEditText.getText().toString());
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ChangeDetailsActivity.this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
                }
            });

            builder.show();
        }
        else if (key == 2) { //Password
            builder.setTitle("Change Password");
            builder.setMessage("Current Password: Blah");
            builder.setView(subView);
            AlertDialog alertDialog = builder.create();

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    textInfo.setText(subEditText.getText().toString());
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ChangeDetailsActivity.this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
                }
            });

            builder.show();
        }
        else if (key == 3) { //Email
            builder.setTitle("Change Email");
            builder.setMessage("Current Email: Blah");
            builder.setView(subView);
            AlertDialog alertDialog = builder.create();

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    textInfo.setText(subEditText.getText().toString());
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ChangeDetailsActivity.this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
                }
            });

            builder.show();
        }
        else if (key == 4) { //PhoneNumber
            builder.setTitle("Change Phone Number");
            builder.setMessage("Current Phone Number: Blah");
            builder.setView(subView);
            AlertDialog alertDialog = builder.create();

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    textInfo.setText(subEditText.getText().toString());
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ChangeDetailsActivity.this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
                }
            });

            builder.show();
        }

    }

    }