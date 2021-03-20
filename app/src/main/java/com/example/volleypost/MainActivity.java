package com.example.volleypost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity implements Response.Listener<JsonObjectRequest>,Response.ErrorListener {

    private EditText etId, etName, etSport;
    private Button btnSubmit;

    private RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        etSport = (EditText) findViewById(R.id.etSport);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        request = Volley.newRequestQueue(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }

            private void cargarWebService() {

                String url = "http://192.168.1.104:8081/BDRemota/wsJSONRegistro.php?id=" + etId.getText().toString() +
                        "&name=" + etName.getText().toString() +
                        "&sport=" + etSport.getText().toString();


                jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,null,null);
                request.add(jsonObjectRequest);

            }
        });


    }
    @Override
    public void onResponse(JsonObjectRequest response) {
        Toast.makeText(this,"Exito",Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
    }


}