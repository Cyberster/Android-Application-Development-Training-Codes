package com.w3epic.day4intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etData = findViewById(R.id.etData);
    }

    public void btnChangeActivityOnClickHandler(View view) {
        Intent main2activity = new Intent(this, Main2Activity.class);
        main2activity.putExtra("data", etData.getText().toString());
        startActivity(main2activity);
    }
}
