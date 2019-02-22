package com.w3epic.day11sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    SQLiteHelper dbHelper;
    SQLiteDatabase db;

    ListView lvUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        try {
            dbHelper = new SQLiteHelper(this);

            // Gets the data repository in write mode
            db = dbHelper.getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {dbHelper.COL_ID, dbHelper.COL_EMAIL, dbHelper.COL_NAME};

            // Filter results WHERE "title" = 'My Title'
            //String selection = dbHelper.COL_EMAIL + " = ? AND " + dbHelper.COL_PASS + " = ? ";
            //String[] selectionArgs = {};

            // How you want the results sorted in the resulting Cursor
            String sortOrder = dbHelper.COL_ID + " ASC";

            Cursor cursor = db.query(
                    dbHelper.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );

            List<String> userList = new ArrayList<String>();
            while (cursor.moveToNext()) {
                StringBuilder row = new StringBuilder();

                long id = cursor.getLong(cursor.getColumnIndexOrThrow(dbHelper.COL_ID));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COL_EMAIL));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COL_NAME));

                row.append("ID: " + id + "\n");
                row.append("Email: " + email + "\n");
                row.append("Name: " + name);

                userList.add(row.toString());
            }
            cursor.close();

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    userList
            );

            lvUserList = findViewById(R.id.lvUserList);
            lvUserList.setAdapter(arrayAdapter);
        } catch (Exception e) {
            Log.d("UserListActivity", e.getMessage());
        }
    }
}
