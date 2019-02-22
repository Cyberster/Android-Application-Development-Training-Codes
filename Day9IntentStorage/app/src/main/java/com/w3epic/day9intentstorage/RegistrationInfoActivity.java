package com.w3epic.day9intentstorage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    try {
        // save to storage
        File f = Environment.getExternalStorageDirectory();
        File dir = new File(f, "my_directory");

        if (!dir.exists()) {
            dir.mkdir();
        } else {
            Toast.makeText(RegistrationInfoActivity.this,"Directory already exists", Toast.LENGTH_SHORT).show();
        }

        File f1 =  new File(dir, "registration_info.txt");


            FileWriter fileWriter = new FileWriter(f1);

            fileWriter.append("Username: " + username + "\n");
            fileWriter.append("Password: " + password + "\n");
            fileWriter.append("First name: " + firstName + "\n");
            fileWriter.append("Last name: " + lastName + "\n");
            fileWriter.append("College: " + college + "\n");
            fileWriter.append("Stream: " + stream + "\n");
            fileWriter.append("Email: " + email + "\n");
            fileWriter.append("Phone: " + phone + "\n");

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("RegistrationInfoActivity", e.getMessage());
        }
    }
}
