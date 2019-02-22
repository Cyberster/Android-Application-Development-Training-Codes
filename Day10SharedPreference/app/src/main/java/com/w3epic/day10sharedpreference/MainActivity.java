package com.w3epic.day10sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etWord;
    EditText etMeaning;

    public static final String PREF_FILE_KEY = "mypref";
    public static final String WORD = "word";
    public static final String MEANING = "meaning";

    // https://developer.android.com/training/data-storage/shared-preferences#java
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);

        sharedPreferences = getSharedPreferences(PREF_FILE_KEY, Context.MODE_PRIVATE);
    }

    public void btnSaveOnClickHandler(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WORD, etWord.getText().toString());
        editor.putString(MEANING, etMeaning.getText().toString());
        editor.commit(); // or use apply()
    }

    public void btnRetrieveOnClickHandler(View view) {
        if (sharedPreferences.contains(WORD)) {
            // https://developer.android.com/reference/android/content/SharedPreferences#getString(java.lang.String,%20java.lang.String)
            etMeaning.setText(sharedPreferences.getString(MEANING, ""));
        }
    }

    public void btnClearOnClickHandler(View view) {
        etWord.setText("");
        etMeaning.setText("");
    }
}
