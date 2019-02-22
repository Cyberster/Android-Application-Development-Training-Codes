package com.w3epic.day13firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText etEmail;
    EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);

        mAuth = FirebaseAuth.getInstance();

        // setting email and password if come from register activity
        String caller = getIntent().getStringExtra("caller");
        if (caller != null && caller.equals("RegisterActivity")) {
            etEmail.setText(getIntent().getStringExtra("email"));
            etPass.setText(getIntent().getStringExtra("pass"));
        }

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Toast.makeText(MainActivity.this,user.getEmail() + " is signed in.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this,"No user is signed in, please sign in.", Toast.LENGTH_LONG).show();
        }
    }

    public void btnLoginOnClick(View view) {
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        mAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this,"Authentication Successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"Authentication failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }

    public void btnRegisterOnClick(View view) {
        Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
        registerIntent.putExtra("caller", this.getClass().getSimpleName());
        registerIntent.putExtra("email", etEmail.getText().toString());
        registerIntent.putExtra("pass", etPass.getText().toString());
        startActivity(registerIntent);
    }
}
