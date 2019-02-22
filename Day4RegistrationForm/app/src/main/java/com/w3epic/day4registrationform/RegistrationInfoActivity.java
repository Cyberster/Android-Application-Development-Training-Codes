package com.w3epic.day4registrationform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RegistrationInfoActivity extends AppCompatActivity {
    TextView tvUsername;
    TextView tvPassword;
    TextView tvConfirmPassword;
    TextView tvFirstName;
    TextView tvLastName;
    TextView tvCollege;
    TextView tvStream;
    TextView tvEmail;
    TextView tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_info);

        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        String confirmPassword = getIntent().getStringExtra("confirmPassword");
        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        String college = getIntent().getStringExtra("college");
        String stream = getIntent().getStringExtra("stream");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");

        tvUsername = findViewById(R.id.tvUsername);
        tvPassword = findViewById(R.id.tvPassword);
        tvConfirmPassword = findViewById(R.id.tvConfirmPassword);
        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvCollege = findViewById(R.id.tvCollege);
        tvStream = findViewById(R.id.tvStream);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);

        tvUsername.setText(username);
        tvPassword.setText(password);
        tvConfirmPassword.setText(confirmPassword);
        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);
        tvCollege.setText(college);
        tvStream.setText(stream);
        tvEmail.setText(email);
        tvPhone.setText(phone);
    }
}
