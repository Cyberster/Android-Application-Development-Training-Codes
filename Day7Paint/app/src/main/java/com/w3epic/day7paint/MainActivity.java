package com.w3epic.day7paint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        PaintView paintView = new PaintView(this);
        setContentView(paintView);
        addContentView(paintView.button, paintView.layoutParams);
    }
}
