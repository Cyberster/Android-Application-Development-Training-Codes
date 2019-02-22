package com.w3epic.day3calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvScreen;
    float operandX;
    float operandY;
    float result;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScreen = findViewById(R.id.tvScreen);

        operandX = 0;
        operandY = 0;
        result = 0;
    }

    public void btnOneOnClickHandler(View view) {
        if (result != 0) {
            result = 0;
            tvScreen.setText("");
        }
        tvScreen.append("1");
    }

    public void btnTwoOnClickHandler(View view) {
        if (result != 0) {
            result = 0;
            tvScreen.setText("");
        }
        tvScreen.append("2");
    }

    public void btnThreeOnClickHandler(View view) {
        if (result != 0) {
            result = 0;
            tvScreen.setText("");
        }
        tvScreen.append("3");
    }

    public void btnFourOnClickHandler(View view) {
        if (result != 0) {
            result = 0;
            tvScreen.setText("");
        }
        tvScreen.append("4");
    }

    public void btnFiveOnClickHandler(View view) {
        if (result != 0) {
            result = 0;
            tvScreen.setText("");
        }
        tvScreen.append("5");
    }

    public void btnSixOnClickHandler(View view) {
        if (result != 0) {
            result = 0;
            tvScreen.setText("");
        }
        tvScreen.append("6");
    }

    public void btnSevenOnClickHandler(View view) {
        if (result != 0) {
            result = 0;
            tvScreen.setText("");
        }
        tvScreen.append("7");
    }

    public void btnEightOnClickHandler(View view) {
        if (result != 0) {
            result = 0;
            tvScreen.setText("");
        }
        tvScreen.append("8");
    }

    public void btnNineOnClickHandler(View view) {
        if (result != 0) {
            result = 0;
            tvScreen.setText("");
        }
        tvScreen.append("9");
    }

    public void btnZeroOnClickHandler(View view) {
        if (result != 0) {
            result = 0;
            tvScreen.setText("");
        }
        tvScreen.append("0");
    }

    public void btnPointOnClickHandler(View view) {
        if (result != 0) {
            result = 0;
            tvScreen.setText("");
        }
        tvScreen.append(".");
    }

    public void btnAddOnClickHandler(View view) {
        operation = "add";
        operandX = Float.parseFloat(tvScreen.getText().toString());
        tvScreen.setText("");
    }

    public void btnSubOnClickHandler(View view) {
        operation = "sub";
        operandX = Float.parseFloat(tvScreen.getText().toString());
        tvScreen.setText("");
    }

    public void btnMulOnClickHandler(View view) {
        operation = "mul";
        operandX = Float.parseFloat(tvScreen.getText().toString());
        tvScreen.setText("");
    }

    public void btnDivOnClickHandler(View view) {
        operation = "div";
        operandX = Float.parseFloat(tvScreen.getText().toString());
        tvScreen.setText("");
    }

    public void btnClearOnClickHandler(View view) {
        tvScreen.setText("");
        operandX = 0;
        operandY = 0;
        result = 0;
    }

    public void btnBackspaceOnClickHandler(View view) {
        String screen = tvScreen.getText().toString();

        if (!screen.isEmpty()) {
            screen = screen.substring(0, screen.length() - 1);
            tvScreen.setText(screen);
        }
    }

    public void btnEqualOnClickHandler(View view) {
        operandY = Float.parseFloat(tvScreen.getText().toString());

        if (operation.equals("add")) {
            result = operandX + operandY;
        } else if (operation.equals("sub")) {
            result = operandX - operandY;
        } else if (operation.equals("mul")) {
            result = operandX * operandY;
        } else if (operation.equals("div")) {
            result = operandX / operandY;
        }

        tvScreen.setText(Float.toString(result));
    }
}
