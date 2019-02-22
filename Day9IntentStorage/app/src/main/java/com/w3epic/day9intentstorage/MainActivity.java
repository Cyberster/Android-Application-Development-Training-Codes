package com.w3epic.day9intentstorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    EditText etConfirmPassword;
    EditText etFirstName;
    EditText etLastName;
    EditText etCollege;
    EditText etStream;
    EditText etEmail;
    EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etCollege = findViewById(R.id.etCollege);
        etStream = findViewById(R.id.etStream);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
    }

    public void btnRegisterOnClickHandler(View view) {
        Intent regInfoIntent = new Intent(this, RegistrationInfoActivity.class);

        regInfoIntent.putExtra("username", etUsername.getText().toString());
        regInfoIntent.putExtra("password", etPassword.getText().toString());
        regInfoIntent.putExtra("confirmPassword", etConfirmPassword.getText().toString());
        regInfoIntent.putExtra("firstName", etFirstName.getText().toString());
        regInfoIntent.putExtra("lastName", etLastName.getText().toString());
        regInfoIntent.putExtra("college", etCollege.getText().toString());
        regInfoIntent.putExtra("stream", etStream.getText().toString());
        regInfoIntent.putExtra("email", etEmail.getText().toString());
        regInfoIntent.putExtra("phone", etPhone.getText().toString());

        try {
            startActivity(regInfoIntent);
        } catch (Exception e) {
            Log.d("MainActivity", e.getMessage());
        }

        Toast.makeText(MainActivity.this, "Registred successfully!", Toast.LENGTH_SHORT).show();
    }
}
