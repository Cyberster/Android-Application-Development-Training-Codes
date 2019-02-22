package com.w3epic.day5inplicitintents_sms;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etTo;
    EditText etMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTo = findViewById(R.id.etTo);
        etMsg = findViewById(R.id.etMessage);
    }

    public void btnSendOnClickHandler(View view) {
        String to = etTo.getText().toString();
        String msg = etMsg.getText().toString();

//        Intent sms = new Intent(Intent.ACTION_SENDTO);
//        sms.setData(Uri.parse("smsto:" + to));
//        sms.putExtra("sms_body", msg);
//        startActivity(sms);

        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(to, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage().toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
}
