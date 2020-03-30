package com.w3epic.day8bluetoothdiscovery;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ListView lvDevices;
    BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> bluetoothDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDevices = findViewById(R.id.lvDevices);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Log.d("debug", "Device doesn't support Bluetooth");
        }
    }

    public void btnOnClickHandler(View view) {
        if (!bluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 0);
        } else {
            Log.d("debug", "Already enabled");
            Toast.makeText(MainActivity.this, "Already enabled", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnVisibleOnClickHandler(View view) {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(intent, 0);
    }

    public void btnDeviceUseOnClickHandler(View view) {
        bluetoothDevices = bluetoothAdapter.getBondedDevices();
        ArrayList arrayList= new ArrayList();

        for (BluetoothDevice bt:bluetoothDevices) {
            arrayList.add(bt.getName());
        }
        Log.d("debug", arrayList.toString());

        ArrayAdapter arrayAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                arrayList
        );

        lvDevices.setAdapter(arrayAdapter);
    }

    public void btnOffOnClickHandler(View view) {
        if (!bluetoothAdapter.isEnabled()) {
            Log.d("debug", "Already disabled");
            Toast.makeText(MainActivity.this, "Already disabled", Toast.LENGTH_SHORT).show();
        } else {
            bluetoothAdapter.disable();
        }
    }

}
