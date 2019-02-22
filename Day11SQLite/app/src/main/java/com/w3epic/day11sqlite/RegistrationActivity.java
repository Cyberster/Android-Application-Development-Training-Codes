package com.w3epic.day11sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    SQLiteHelper dbHelper;
    SQLiteDatabase db;

    EditText etName;
    EditText etEmail;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbHelper = new SQLiteHelper(this);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    public void btnRegisterOnCLickHandler(View view) {
        // get entered data from registration form
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && password.length() >= 8) {
            // check if email has already registred
            db = dbHelper.getReadableDatabase();

            // Filter results WHERE "title" = 'My Title'
            String selection = dbHelper.COL_EMAIL + " = ? ";
            String[] selectionArgs = {email};

            Cursor cursor = db.query(
                    dbHelper.TABLE_NAME,   // The table to query
                    null,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );

            //List itemIds = new ArrayList();
            if (cursor.moveToNext()) {
                Toast.makeText(this, "Email already registred!", Toast.LENGTH_SHORT).show();
            } else {
                // Gets the data repository in write mode
                db = dbHelper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(dbHelper.COL_NAME, name);
                values.put(dbHelper.COL_EMAIL, email);
                values.put(dbHelper.COL_PASS, password);

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(dbHelper.TABLE_NAME, null, values);

                Toast.makeText(this, "Registred successfully!", Toast.LENGTH_SHORT).show();

                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
            }

            cursor.close();
        } else {
            String errorMsg = "Name and email should not be empty. Password should contains at least 8 characters.";
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    }
}
