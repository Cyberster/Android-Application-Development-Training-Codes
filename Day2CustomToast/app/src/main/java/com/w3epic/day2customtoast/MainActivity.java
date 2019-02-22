package com.w3epic.day2customtoast;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClickMeOnClickHandler(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.llToast));

        //ImageView ivToast = (ImageView) layout.findViewById(R.id.ivToast);
        //ivToast.setImageResource(R.drawable.toast);

        TextView tvToast = (TextView) layout.findViewById(R.id.tvToast);
        tvToast.setText("Hello from custom toast.");
        //tvToast.setBackgroundColor(Color.GRAY);
        tvToast.setBackgroundResource(R.drawable.toast);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
