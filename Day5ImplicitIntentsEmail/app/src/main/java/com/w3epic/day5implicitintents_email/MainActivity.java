package com.w3epic.day5implicitintents_email;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etTo;
    EditText etSub;
    EditText etMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTo = findViewById(R.id.etTo);
        etSub = findViewById(R.id.etSubject);
        etMsg = findViewById(R.id.etMessage);
    }

    public void btnSendOnClickHandler(View view) {
        String to = etTo.getText().toString();
        String sub = etSub.getText().toString();
        String msg = etMsg.getText().toString();

//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, sub);
//        emailIntent.putExtra(Intent.EXTRA_TEXT, msg);
//        emailIntent.setType("message/rfc822");
//
//        startActivity(Intent.createChooser(emailIntent, "Choose an email client: "));

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + to));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, sub);
        emailIntent.putExtra(Intent.EXTRA_TEXT, msg);

        try {
            startActivity(emailIntent);
        } catch (Exception e) {
            Log.d("MainActivity", e.getMessage());
        }
    }
}

