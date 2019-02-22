package com.w3epic.day3minicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // declaring global variables
    EditText etNumber1;
    EditText etNumber2;

    EditText etResultAdd;
    EditText etResultSub;
    EditText etResultMul;

    float number1;
    float number2;
    float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing global variables
        etNumber1   = findViewById(R.id.etNumber1);
        etNumber2   = findViewById(R.id.etNumber2);
        etResultAdd = findViewById(R.id.etResultAdd);
        etResultSub = findViewById(R.id.etResultSub);
        etResultMul = findViewById(R.id.etResultMul);
    }

    public void addOnClickHandler(View view) {
        number1 = Float.parseFloat(etNumber1.getText().toString());
        number2 = Float.parseFloat(etNumber2.getText().toString());

        result = number1 + number2;

        etResultAdd.setText(Float.toString(result));
    }

    public void subOnClickHandler(View view) {
        number1 = Float.parseFloat(etNumber1.getText().toString());
        number2 = Float.parseFloat(etNumber2.getText().toString());

        result = number1 - number2;

        etResultSub.setText(Float.toString(result));
    }

    public void mulOnClickHandler(View view) {
        number1 = Float.parseFloat(etNumber1.getText().toString());
        number2 = Float.parseFloat(etNumber2.getText().toString());

        result = number1 * number2;

        etResultMul.setText(Float.toString(result));
    }

}
