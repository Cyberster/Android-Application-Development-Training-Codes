package com.w3epic.day10loginintentsharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        final String PREF_FILE_KEY = "mypref";
        final String Useranme = "username";
        final String Password = "password";

        sharedPreferences = getSharedPreferences(PREF_FILE_KEY, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(Useranme)) {
            //https://developer.android.com/reference/android/content/SharedPreferences#getString(java.lang.String,%20java.lang.String)
            etUsername.setText(sharedPreferences.getString(Useranme, "not found"));
        }
        if (sharedPreferences.contains(Password)) {
            //https://developer.android.com/reference/android/content/SharedPreferences#getString(java.lang.String,%20java.lang.String)
            etPassword.setText(sharedPreferences.getString(Password, "not found"));
        }
    }
}
