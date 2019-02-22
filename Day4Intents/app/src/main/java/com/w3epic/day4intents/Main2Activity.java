package com.w3epic.day4intents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView tvReceivedText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvReceivedText = findViewById(R.id.tvReceivedText);
        String dataReceived = "Data received: " + getIntent().getStringExtra("data");
        tvReceivedText.setText(dataReceived);
    }
}
