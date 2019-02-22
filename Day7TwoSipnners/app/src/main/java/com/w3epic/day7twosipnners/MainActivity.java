package com.w3epic.day7twosipnners;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] itemList1 = {"Select an item", "Girls", "Boys"};
    String[] girl_names = {"Select an item", "Sulagna", "Sunita", "Gopa", "Sunita"};
    String[] boy_names = {"Select an item", "Ankan", "Avick", "Joyjit", "Arpan"};

    Spinner spinner1;
    Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = findViewById(R.id.spinner1);
        //spinner1.setBackgroundResource(android.R.drawable.spinner_dropdown_background);
        spinner2 = findViewById(R.id.spinner2);
        //spinner2.setBackgroundResource(android.R.drawable.spinner_dropdown_background);

        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, itemList1);
        final ArrayAdapter<String> arrayAdapterGirls = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, girl_names);
        final ArrayAdapter<String> arrayAdapterBoys = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, boy_names);

        spinner1.setAdapter(arrayAdapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String list1_item = spinner1.getSelectedItem().toString();

                if (i != 0) Toast.makeText(MainActivity.this, list1_item, Toast.LENGTH_SHORT).show();

                if (list1_item.equals("Girls")) {
                    spinner2.setAdapter(arrayAdapterGirls);
                } else {
                    spinner2.setAdapter(arrayAdapterBoys);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String list2_item = spinner2.getSelectedItem().toString();

                if (i != 0) Toast.makeText(MainActivity.this, list2_item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.setting:
                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
