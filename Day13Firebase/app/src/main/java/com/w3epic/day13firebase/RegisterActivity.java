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

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText etEmail;
    EditText etPass;
    EditText etConfPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etConfPass = findViewById(R.id.etConfPass);

        mAuth = FirebaseAuth.getInstance();

        // setting email and password if come from main activity
        String caller = getIntent().getStringExtra("caller");
        if (caller != null && caller.equals("MainActivity")) {
            etEmail.setText(getIntent().getStringExtra("email"));
            etPass.setText(getIntent().getStringExtra("pass"));
        }
    }

    public void btnRegisterOnClick(View view) {
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        String confPass = etConfPass.getText().toString();

        if (!pass.equals(confPass)) {
            String errorMsg = "Password and confirm password doesn't match!";
            Toast.makeText(RegisterActivity.this, errorMsg, Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this,"Registration Successful", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RegisterActivity.this,"Registration failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        }
    }

    public void btnLoginOnClick(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("caller", this.getClass().getSimpleName());
        mainIntent.putExtra("email", etEmail.getText().toString());
        mainIntent.putExtra("pass", etPass.getText().toString());
        startActivity(mainIntent);
    }
}
