package com.w3epic.day4batanimation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView ivBat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBat = findViewById(R.id.ivBat);

        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 2000L);*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final AnimationDrawable drawable = (AnimationDrawable) getResources().getDrawable(R.drawable.bat_anim);
        ivBat.setImageDrawable(drawable);
        drawable.start();

        //Handler handler = new Handler();

        ivBat.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Handle your animation ending here
                //ivBat.clearAnimation(); // reset animation
                drawable.stop(); // stop animation
            }
        }, 5000);

        return super.onTouchEvent(event);
    }
}
