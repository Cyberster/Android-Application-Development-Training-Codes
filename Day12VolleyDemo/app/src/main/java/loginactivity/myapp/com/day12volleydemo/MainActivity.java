package loginactivity.myapp.com.day12volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etEmail;
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        tvText = findViewById(R.id.tvText);
    }

    public void btnSaveOnClickHandler(View view) {
        // https://developer.android.com/training/volley/simple
        // https://stackoverflow.com/questions/33573803/how-to-send-a-post-request-using-volley-with-string-body

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://w3epic.com/sandbox/process.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvText.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })  {
        // This is the syntax for creating an instance of anonymous class that extends Handler. This is part of Java.
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("name", etName.getText().toString());
            params.put("email", etEmail.getText().toString());

            return params;
        }
    };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
