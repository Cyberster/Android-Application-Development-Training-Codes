package com.w3epic.day7paintapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //PaintView paintView = new PaintView(this);
    PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        paintView = new PaintView(this);
        setContentView(paintView);
        addContentView(paintView.toolsPanel, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        //addContentView(paintView.btnColorRed, paintView.layoutParams);
        //addContentView(paintView.btnColorGreen, paintView.layoutParams);
        //addContentView(paintView.btnColorBlue, paintView.layoutParams);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.miErase) {
            //paintView.getPath().reset();
           // paintView.postInvalidate();
            paintView.eraseAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
