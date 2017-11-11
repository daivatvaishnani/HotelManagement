package com.example.shivam.HotelManagement;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shivam.HotelManagement.Activity.MainActivity;

public class splashActivity extends AppCompatActivity {

    TextView welcome;
    ImageView skyline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        welcome = (TextView)findViewById(R.id.welcome);
        skyline = (ImageView) findViewById(R.id.skyline);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);

        welcome.startAnimation(myanim);
        skyline.startAnimation(myanim);

        final Intent i = new Intent(this, MainActivity.class);
        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(5000);
                }catch(Exception e){}
                finally{
                    startActivity(i);
                    finish();
                }
            }

        };
        timer.start();
    }
}
