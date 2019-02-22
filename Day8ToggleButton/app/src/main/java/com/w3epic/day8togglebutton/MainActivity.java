package com.w3epic.day8togglebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnToggle;
    Button btn1;
    Button btn2;
    Button btn3;

    boolean toggleState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToggle   = findViewById(R.id.btnToggle);
        btn1        = findViewById(R.id.btn1);
        btn2        = findViewById(R.id.btn2);
        btn3        = findViewById(R.id.btn3);

        toggleState = false;
    }

    public void btnToggleOnClickHandler(View view) {
        if (toggleState == false) {
            toggleState = true;

            btn1.setVisibility(View.INVISIBLE);
            btn2.setVisibility(View.INVISIBLE);
            btn3.setVisibility(View.INVISIBLE);
        } else {
            toggleState = false;

            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
        }
    }
}
