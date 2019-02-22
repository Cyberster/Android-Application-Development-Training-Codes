package com.w3epic.day11sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView tvMessage = findViewById(R.id.tvMessage);
        String email = getIntent().getStringExtra("email");
        tvMessage.setText("Welcome \n" + email);
    }

    public void btnLogoutOnClickHandler(View view) {
        Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void btnViewUserListOnClickHandler(View view) {
        Intent viewUserListIntent = new Intent(this, UserListActivity.class);
        startActivity(viewUserListIntent);
    }
}
