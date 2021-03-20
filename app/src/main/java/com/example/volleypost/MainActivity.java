package com.example.volleypost;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity  {

    private EditText etId, etName, etSport;
    private Button btnSubmit;

    ProgressDialog progreso;

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

                progreso= new ProgressDialog(MainActivity.this);
                progreso.setMessage("Cargando...");
                progreso.show();

                String url = "http://192.168.1.104:8081/BDRemota/wsJSONRegistro.php?id=" + etId.getText().toString() +
                        "&name=" + etName.getText().toString() +
                        "&sport=" + etSport.getText().toString();


                jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progreso.hide();
                                Toast.makeText(getApplicationContext(),"Exito al guardar :) "+ response.toString(), Toast.LENGTH_LONG).show();
                                etId.setText(" ");
                                etName.setText(" ");
                                etSport.setText(" ");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progreso.hide();
                        Toast.makeText(getApplicationContext(),"Error :( "+error.toString(), Toast.LENGTH_SHORT).show();
                        Log.i("Error",error.toString());

                    }
                });
                request.add(jsonObjectRequest);

            }


        });

    }


}