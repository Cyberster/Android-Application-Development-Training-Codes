package com.w3epic.day11sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

// https://developer.android.com/training/data-storage/sqlite#java

public class MainActivity extends AppCompatActivity {
    SQLiteHelper dbHelper;
    SQLiteDatabase db;

    EditText etEmail;
    EditText etPassword;

    public static boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new SQLiteHelper(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    public void btnLoginOnCLickHandler(View view) {
        // get entered data from login form
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (login(email, password)) {
            isLoggedIn = true;
            Toast.makeText(this, "Logged in successfully!", Toast.LENGTH_SHORT).show();

            Intent dashboardIntent = new Intent(MainActivity.this, DashboardActivity.class);
            dashboardIntent.putExtra("email", email);
            startActivity(dashboardIntent);
        } else {
            Toast.makeText(this, "Invalid email/password!", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnRegisterOnCLickHandler(View view) {
        Intent regIntent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(regIntent);
    }

    public boolean login (String email, String password) {
        boolean retVal = false;
        try {
            // Gets the data repository in write mode
            db = dbHelper.getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {};

            // Filter results WHERE "title" = 'My Title'
            String selection = dbHelper.COL_EMAIL + " = ? AND " + dbHelper.COL_PASS + " = ? ";
            String[] selectionArgs = {email, password};

            // How you want the results sorted in the resulting Cursor
            //String sortOrder = dbHelper.COL_NAME + " DESC";

            Cursor cursor = db.query(
                    dbHelper.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );

            //List itemIds = new ArrayList();
            if (cursor.moveToNext()) {
                retVal = true;
            } else {
                retVal = false;
            }
            cursor.close();
        } catch (Exception e) {
            Log.d("MainActivity", e.getMessage());
        }

        return retVal;
    }
}
