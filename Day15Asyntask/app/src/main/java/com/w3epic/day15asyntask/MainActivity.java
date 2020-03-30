package com.w3epic.day15asyntask;

import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);

    }

    public void btnSubmitOnClickHandler(View view) {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        //String uri = "http://w3epic.com/sandbox/process_get.php?name=" + name + "&email=" + email;
        //String uri = "http://w3epic.com/sandbox/process.php";
        String uri = "http:/192.168.0.16/android/post.php";
        new MultiTsak().execute(uri);
    }

    class MultiTsak extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String urlParameters  = "name=Arpan&email=arp14@yahoo.com";

//                // GET request *********************************************************************
//                // For GET only - START
//                URL url = new URL(strings[0] + '?' + urlParameters);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("GET");
//                httpURLConnection.connect();
//                // For GET only - END

                // POST request ********************************************************************
                byte[] postData       = urlParameters.getBytes( "UTF-8" );
                String request        = strings[0];
                URL    url            = new URL( request );
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                // For POST only - START
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(postData);
                os.flush();
                os.close();
                // For POST only - END

                // common for both GET and POST ****************************************************
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                Log.d("MainActivity", sb.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                Log.d("MainActivity", e.getMessage());
            }

            return null;
        }
    }
}
