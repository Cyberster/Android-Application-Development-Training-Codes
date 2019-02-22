package com.w3epic.day10loginintentsharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    Spinner spnDomain;
    String[] domains = {"gmail.com","yahoo.com","hotmail.com","rediffmail.com"};

    public static final String PREF_FILE_KEY = "mypref";
    public static final String username = "username";
    public static final String password = "password";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        spnDomain = findViewById(R.id.spnDomain);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            domains
        );

        spnDomain.setAdapter(arrayAdapter);

        sharedPreferences = getSharedPreferences(PREF_FILE_KEY, Context.MODE_PRIVATE);
    }

    public void login(View view) {
        String user = etUsername.getText().toString() + "@" + spnDomain.getSelectedItem().toString();
        String pwd = etPassword.getText().toString();

        if (pwd.length() >= 8 && pwd.charAt(0) >= 'A') {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(username, user);
            editor.putString(password, pwd);
            editor.commit(); // or use apply()

            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this,
                "password should be greater than equals to 8 characters long and should be started with capital letters",
                Toast.LENGTH_SHORT).show();
        }
    }
}
