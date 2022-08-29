package com.ium.example.booking.controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.ium.example.booking.MainActivity;
import com.ium.example.booking.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.ViewTransitionController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    EditText emailValue;
    EditText passwordValue;
    Button loginButton;
    RequestQueue requestQueue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setBackgroundColor(0xFFa0acde);
        emailValue = findViewById(R.id.activity_login_input_email);
        passwordValue = findViewById(R.id.activity_login_input_password);
        loginButton = findViewById(R.id.activity_login_button);
        loginButton.setOnClickListener(this::onLoginClick);
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
    }

    public void onLoginClick(View view) {
        if(view.getId() == R.id.activity_login_button) {
            String email = emailValue.getText().toString();
            String password = passwordValue.getText().toString();

            if (email.isEmpty()) {
                emailValue.setError(getString(R.string.activity_login_input_email_error_empty));
                emailValue.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                passwordValue.setError(getString(R.string.activity_login_input_email_error_empty));
                passwordValue.requestFocus();
                return;
            }

            StringRequest stringRequest = new StringRequest(Request.Method.POST, MyURL.URLPOST, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response", response);
                        if(response.equals("Login effettuato")){
                            azione(email);
                        }else {
                            emailValue.setError(getString(R.string.activity_login_input_email_error_invalid));
                            emailValue.requestFocus();
                            passwordValue.setError(getString(R.string.activity_login_input_password_error_invalid));
                            passwordValue.requestFocus();
                            return;
                        }
                    }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }
                    }) {
                //Aggiungo i parametri alla richiesta
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("action", "androidL");
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };
            requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(stringRequest);

        }
    }

    private void azione(String email){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("account", email);
        startActivity(intent);
    }

    public void onGuestClick(View view) {
        if(view.getId() == R.id.activity_login_guest_button) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, MyURL.URLPOST, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("Response", response);
                    if (response.equals("Login effettuato")) {
                        azione("Ospite");
                    } else {
                        return;
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }) {
                //Aggiungo i parametri alla richiesta
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("action", "ospiteL");
                    return params;
                }
            };
            requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(stringRequest);
        }
    }
}
